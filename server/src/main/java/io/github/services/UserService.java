package io.github.services;


import io.github.stubs.user.User;
import io.github.stubs.user.userGrpc;
import io.grpc.stub.StreamObserver;

public class UserService extends userGrpc.userImplBase {

    @Override
    public void login(User.LoginRequest request, StreamObserver<User.APIResponse> responseObserver) {

        System.out.println("Inside login");

        String username = request.getUsername();
        String password = request.getPassword();

        User.APIResponse.Builder response = User.APIResponse.newBuilder();
        if(username.equals(password)) {
            // return success message
            response.setResponseCode(0).setResponseMessage("SUCCESS");
        }
        else {
            response.setResponseCode(100).setResponseMessage("INVALID PASSWORD");
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void logout(User.Empty request, StreamObserver<User.APIResponse> responseObserver) {

    }



}
