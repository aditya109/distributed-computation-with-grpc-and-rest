package io.github.restserver.controllers;

import io.github.restserver.helper.PathProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

@Component
@PropertySource("classpath:application.properties")
public class GrpcServerScalingController {

    @Autowired
    private Environment env;

    @Autowired
    private PathProvider pathProvider;

    private ArrayList<Integer> portList;

    public GrpcServerScalingController() {
        this.portList = new ArrayList<>();
    }

    public ArrayList<Integer> getPortList() {
        return portList;
    }

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

    private void getFreePortList(int requirements) {
        requirements = requirements < (Runtime.getRuntime().availableProcessors() - 2) ? requirements : 4;
        for (int i = 0; i < requirements; i++) {
            portList.add(findFreePort());
        }
    }

    public void grpcServerScaleUp(boolean isFirstInstance) throws IOException {
        String envThreshold = env.getProperty("server_threshold");
        if (isFirstInstance) {
            if (envThreshold != null) {
                int requirements = Integer.parseInt(envThreshold);
                getFreePortList(requirements);
            }
            int firstInstancePort = portList.get(0);
            System.out.println("=================================");
            System.out.println("Spawning gRPC server at " + firstInstancePort);
            spawnGrpcServer(firstInstancePort);
        } else {
            for (int i = 1; i < portList.size(); i++) {
                System.out.println("=================================");
                System.out.println("Spawning gRPC server at " + portList.get(i));
                spawnGrpcServer(portList.get(i));
            }
        }
    }

    public void spawnGrpcServer(int port) throws IOException {
        String spawnScriptPath = pathProvider.provideScriptPath() + File.separator + "spawn.py";
        String cmd = "python " + spawnScriptPath + " " + port;
        System.out.println(cmd);
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        System.out.println("=================================");

    }

    public void grpcServerScaleDown() throws IOException {
        for (int port : this.portList) {
            killGrpcServer(port);
            System.out.println("Worker gRPC on " + port + " was killed.");
        }
    }

    public void killGrpcServer(int port) throws IOException {
        String spawnScriptPath = pathProvider.provideScriptPath() + File.separator + "kill.py";
        String cmd = "python " + spawnScriptPath + " " + port;
        System.out.println(cmd);
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        System.out.println("=================================");
    }
}
