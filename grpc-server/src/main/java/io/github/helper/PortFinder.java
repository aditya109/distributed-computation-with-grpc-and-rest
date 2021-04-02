package io.github.helper;

import io.github.middleware.LoggerProvider;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

public class PortFinder {
    private static Logger logger;

    public int findFreePort() {
        // receiving a fresh instance from LoggerProvider
        logger = new LoggerProvider(new PortFinder()).provideLoggerInstance();

        try (ServerSocket socket = new ServerSocket(0)) {
            try {
                socket.setReuseAddress(true);
            } catch (SocketException e) {
                logger.fatal("error in initialization socket");
            }
            int port = socket.getLocalPort();
            try {
                socket.close();
            } catch (IOException e) {
                logger.warn("error in closing socket");
            }
            return port;
        } catch (IOException e) {
            logger.fatal("\"could not find a free TCP/IP port to start embedded Jetty HTTP Server on\"");
        }
        throw new IllegalStateException("Could not find a free TCP/IP port to start embedded Jetty HTTP Server on");
    }
}
