package io.github.restserver.controllers;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClientController {
    public void callmultiplyRowByColumn() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost",9595)
                .usePlaintext()
                .build();

    }
}
