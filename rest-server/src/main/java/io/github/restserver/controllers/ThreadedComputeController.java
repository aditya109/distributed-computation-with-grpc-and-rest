package io.github.restserver.controllers;

import io.github.restserver.helper.DeadlineFootprintHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

@Component
public class ThreadedComputeController {
    @Autowired
    private DeadlineFootprintHelper deadlineFootprintHelper;

    @Autowired
    private GrpcServerScalingController grpcServerScalingController;

    class GrpcClientCallable implements Callable<GrpcResponse> {
        public int port;
        public ArrayList<Long> row;
        public ArrayList<Long> column;
        public int rowId;
        public int columnId;

        public GrpcClientCallable(int port, ArrayList<Long> row, ArrayList<Long> column, int rowId, int columnId) {
            this.port = port;
            this.row = row;
            this.column = column;
            this.rowId = rowId;
            this.columnId = columnId;
        }

        @Override
        public GrpcResponse call() throws Exception {
            GrpcResponse grpcResponse = new GrpcClientController()
                    .callMultiplyRowByColumnUsingAsyncStub(
                            this.port,
                            this.row,
                            this.column,
                            this.rowId,
                            this.columnId
                    );
            return grpcResponse;
        }
    }

    private GrpcClientController grpcClientController;
    private int counter;
    private ArrayList<Integer> portList;
    private boolean isSetupDone;
    private boolean areWorkersUp;
    public ThreadedComputeController() {
        this.grpcClientController = new GrpcClientController();
        this.counter = 0;
        this.isSetupDone = false;
        this.areWorkersUp = false;
    }
    public void setup(ArrayList<ArrayList<Long>> matrixA, ArrayList<ArrayList<Long>> matrixB) throws IOException, InterruptedException {
        if (!this.isSetupDone) {
            this.portList = this.grpcServerScalingController.getPortList();
            grpcServerScalingController.grpcServerScaleUp(true);
            int portEngaged = grpcServerScalingController.getPortList().get(0);
            GrpcResponse grpcResponse = new GrpcClientController().callMultiplyRowByColumnUsingAsyncStub(portEngaged, matrixA.get(0), matrixA.get(0), 0, 0);
            grpcServerScalingController.grpcServerScaleUp(false);
            this.isSetupDone = true;
            this.areWorkersUp = true;
        }
    }
    public ArrayList<ArrayList<Long>> run(ArrayList<ArrayList<Long>> matrixA, ArrayList<ArrayList<Long>> matrixB) throws InterruptedException, ExecutionException, IOException {
        setup(matrixA, matrixB);
        if (! this.areWorkersUp) {
            grpcServerScalingController.grpcServerScaleUp(false);
        }
        long dimension = matrixA.size();
        int workers = deadlineFootprintHelper.computeWorkersRequired(dimension);
        ArrayList<ArrayList<Long>> matrixC = new ArrayList<>();     // resultant matrixC = matrixA * matrixB
        ExecutorService pool = Executors.newFixedThreadPool(workers);
        Set<Future<GrpcResponse>> elements = new HashSet<>();

        int iterativeLimit = matrixA.size();

        for (int i = 0; i < iterativeLimit; i++) {
            for (int j = 0; j < iterativeLimit; j++) {
                Callable<GrpcResponse> e =
                        new GrpcClientCallable(
                                getPortUsingRR(),
                                matrixA.get(i),
                                matrixB.get(j),
                                i,
                                j
                        );
                Future future = pool.submit(e);
                elements.add(future);

            }
        }

        for (int i = 0; i < dimension; i++) {
            ArrayList<Long> row = new ArrayList<>();
            for (int j = 0; j < dimension; j++) {
                row.add(0L);
            }
            matrixC.add(row);
        }

        for (Future<GrpcResponse> e : elements) {
            GrpcResponse temp = e.get();
            matrixC.get(temp.getRowId()).set(temp.getColumnId(), temp.getElement());
        }
        return matrixC;
    }

    public int getPortUsingRR() {
        counter++;
        if (counter == portList.size()) {
            counter = 0;
        }
        return portList.get(counter);
    }
}


