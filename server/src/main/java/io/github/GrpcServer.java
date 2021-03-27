package io.github;

import io.github.core.MultiThread;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
//        ComputeEngine computeEngine = new ComputeEngine();
//        computeEngine.setupNonAsync();
        MultiThread multiThread = new MultiThread();
//        multiThread.test2();
        multiThread.test();
        System.out.println("Execution time: " + (System.currentTimeMillis() - start) + " milliseconds");
//        final int PORT = 9595;
//        Server server = ServerBuilder
//                .forPort(PORT)
//                .addService(new UserService())
//                .addService(new FileUploadService())
//                .build();
//
//        server.start();
//        System.out.println("server started at "+ server.getPort());
//        server.awaitTermination();


    }
}