package io.github;

import io.github.stubs.user.User;
import io.github.stubs.user.userGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {




    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost",9595)
                .usePlaintext()
                .build();

        // stubs - generate from proto

        userGrpc.userBlockingStub userStub = userGrpc.newBlockingStub(channel);

        User.LoginRequest loginRequest = User.LoginRequest.newBuilder().setUsername("RAMasdf").setPassword("RAM").build();

        User.APIResponse response = userStub.login(loginRequest);

        System.out.println(response.getResponseMessage());


    }
}
