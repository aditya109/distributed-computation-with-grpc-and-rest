package io.github.restserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

@Component
@PropertySource("classpath:application.properties")
public class GrpcServerScalingController {

    @Autowired
    private Environment env;

    /**
     * Returns a free port number on localhost.
     * <p>
     * Heavily inspired from org.eclipse.jdt.launching.SocketUtil (to avoid a dependency to JDT just because of this).
     * Slightly improved with close() missing in JDT. And throws exception instead of returning -1.
     *
     * @return a free port number on localhost
     * @throws IllegalStateException if unable to find a free port
     */
    private int findFreePort() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(0);
            socket.setReuseAddress(true);
            int port = socket.getLocalPort();
            try {
                socket.close();
            } catch (IOException e) {
                // Ignore IOException on close()
            }
            return port;
        } catch (IOException e) {
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
        throw new IllegalStateException("Could not find a free TCP/IP port to start embedded Jetty HTTP Server on");
    }

    private List<Integer> getFreePortList(int requirements) {
        requirements = requirements < (Runtime.getRuntime().availableProcessors() - 2) ? requirements : 4;
        List<Integer> freePortList = null;
        for (int i = 0; i < requirements; i++) {
            freePortList.add(findFreePort());
        }
        return freePortList;
    }

    public void grpcServerScaler(boolean isFirstTime){
        if (isFirstTime) {
            int freePort = findFreePort();
            spawnGrpcServer(freePort, true);
        } else {
            int requirements = Integer.parseInt(env.getProperty("server_threshold"));
            List<Integer> freePortList = getFreePortList(requirements);
        }

    }

    public void spawnGrpcServer(int port, boolean doSetup) {
        if (doSetup) {

        }
    }

    public void spawnGrpcServer(int port) {

    }
}
