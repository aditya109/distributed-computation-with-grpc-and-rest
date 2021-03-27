package io.github.restserver.controllers;

import io.github.restserver.stubs.matrix.Matrix;
import io.github.restserver.stubs.matrix.computeGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static io.github.restserver.stubs.matrix.computeGrpc.newBlockingStub;
import static io.github.restserver.stubs.matrix.computeGrpc.newStub;

class GrpcResponse {
    @Override
    public String toString() {
        return "GrpcResponse{" +
                "element=" + element +
                ", rowId=" + rowId +
                ", columnId=" + columnId +
                '}';
    }

    private long element;
    private int rowId;
    private int columnId;

    public long getElement() {
        return element;
    }

    public void setElement(long element) {
        this.element = element;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public GrpcResponse(long element, int rowId, int columnId) {
        this.element = element;
        this.rowId = rowId;
        this.columnId = columnId;
    }
}

public class GrpcClientController {
    public GrpcResponse response;

    public GrpcClientController() {
        this.response = null;
    }

    public void setResponse(GrpcResponse response) {
        this.response = response;
    }

    public GrpcResponse callMultiplyRowByColumnUsingBlockingStub(int port, ArrayList<Long> row, ArrayList<Long> column, int rowId, int colId) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", port)
                .usePlaintext()
                .build();
        computeGrpc.computeStub matrixStub = newStub(channel);
        Matrix.multiplyBlockRequest.Builder builder = Matrix.multiplyBlockRequest.newBuilder();
        for (long i : row) {
            builder = builder.addRow(i);
        }
        for (long i : column) {
            builder = builder.addColumn(i);
        }
        Matrix.multiplyBlockRequest request = builder.setRowId(rowId).setColumnId(colId).build();
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<Matrix.multiplyBlockResponse> responseObserver = new StreamObserver<Matrix.multiplyBlockResponse>() {

            @Override
            public void onNext(Matrix.multiplyBlockResponse multiplyBlockResponse) {
                GrpcResponse response = null;
                response.setElement(multiplyBlockResponse.getElement());
                response.setColumnId(multiplyBlockResponse.getColumnId());
                response.setRowId(multiplyBlockResponse.getRowId());
                setResponse(response);
            }

            @Override
            public void onError(Throwable throwable) {
                Status status = Status.fromThrowable(throwable);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                finishLatch.countDown();
            }
        };
        matrixStub.multiplyRowByColumn(request, responseObserver);
        finishLatch.await();
        return response;
    }

    public GrpcResponse callMultiplyRowByColumnUsingAsyncStub(int port, ArrayList<Long> row, ArrayList<Long> column, int rowId, int colId) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", port)
                .usePlaintext()
                .build();
        computeGrpc.computeBlockingStub matrixStub = newBlockingStub(channel);
        Matrix.multiplyBlockRequest.Builder builder = Matrix.multiplyBlockRequest.newBuilder();
        for (long i : row) {
            builder = builder.addRow(i);
        }
        for (long i : column) {
            builder = builder.addColumn(i);
        }
        Matrix.multiplyBlockRequest request = builder.setRowId(rowId).setColumnId(colId).build();
        Matrix.multiplyBlockResponse multiplyBlockResponse = matrixStub.multiplyRowByColumn(request);
        GrpcResponse response = null;
        response.setElement(multiplyBlockResponse.getElement());
        response.setColumnId(multiplyBlockResponse.getColumnId());
        response.setRowId(multiplyBlockResponse.getRowId());
        setResponse(response);
        return response;
    }
}
