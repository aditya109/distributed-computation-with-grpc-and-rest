package io.github;

import io.github.helper.PortFinder;
import io.github.middleware.LoggerProvider;
import io.github.services.ComputeService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class GrpcServer {
    private static int envPort;
    private static Logger logger;

    private static void getEnvPort(String[] argv) {
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

    public static void main(String[] args) {
        // receiving a fresh instance from LoggerProvider
        logger = new LoggerProvider(new GrpcServer()).provideLoggerInstance();
        int fallbackPort = 8097;
        try {
            fallbackPort = new PortFinder().findFreePort();
        } catch (Exception e) {
            logger.warn("could not find a free TCP/IP port to start embedded Jetty HTTP Server on\n" +
                    "setting it to default port 8097");
        }
        // get port from command line argument
        getEnvPort(args);
        // setting port to 9595
        final int PORT = envPort != -1 ? envPort : fallbackPort;
        logger.info("Port set to " + PORT);
        Server server = ServerBuilder
                // adding port for starting the server
                .forPort(PORT)
                // adding service for the server
                .addService(new ComputeService())
                // completing the build
                .build();
        try {
            // starting the server at set PORT
            server.start();
        } catch (IOException e) {
            logger.debug("IOException encountered in starting gRPC server at port " + PORT);
        }
        logger.info("server successfully started at " + PORT);
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            logger.fatal("something interrupted while gRPC server running at " + PORT + " was awaiting for termination");
        }
    }
}
