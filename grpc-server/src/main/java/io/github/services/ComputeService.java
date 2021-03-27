package io.github.services;

import io.github.engine.AddBlock;
import io.github.engine.MultiplyBlock;
import io.github.helper.MatrixLoader;
import io.github.stubs.matrix.Matrix;
import io.github.stubs.matrix.computeGrpc;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class ComputeService extends computeGrpc.computeImplBase {
    private AddBlock add;
    private MultiplyBlock mult;
    private MatrixLoader matrixLoader;

    public ComputeService() {
        this.matrixLoader = new MatrixLoader();
    }

    @Override
    public void multiplyRowByColumn(Matrix.multiplyBlockRequest request, StreamObserver<Matrix.multiplyBlockResponse> responseObserver) {
        List<Long> row = request.getRowList();
        List<Long> column = request.getColumnList();
        int rowId = request.getRowId();
        int columnId = request.getColumnId();

        mult = new MultiplyBlock(row, column);
        add = new AddBlock(mult.performCompute());
        long element = add.performCompute();

        Matrix.multiplyBlockResponse.Builder response = Matrix.multiplyBlockResponse.newBuilder();
        response.setColumnId(columnId);
        response.setRowId(rowId);
        response.setElement(element);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

}
