package io.github;

import io.github.services.ComputeService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    private static int envPort;

    private static void setEnvPort(String[] argv) {
        String args;
        if (argv.length != 0) {
            args = argv[0];
            String portArgument = args.split(";")[0].split(":")[1];
            envPort = Integer.parseInt(portArgument);
        } else if (System.getProperty("exec.args") != null) {
            args = System.getProperty("exec.args");
            String portArgument = args.split(";")[0].split(":")[1];
            envPort = Integer.parseInt(portArgument);
        } else {
            envPort = -1;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        setEnvPort(args);
        final int PORT = envPort != -1 ? envPort : 9595;
        Server server = ServerBuilder
                .forPort(PORT)
                .addService(new ComputeService())
                .build();
        server.start();
        System.out.println("Server started at " + PORT);
        server.awaitTermination();
    }

}
