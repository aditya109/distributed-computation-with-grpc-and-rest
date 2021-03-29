package io.github.restserver.controllers;

import io.github.restserver.helper.PathProvider;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

@Component
@PropertySource("classpath:application.properties")
public class GrpcServerScalingController {

    private final Environment env;

    private final PathProvider pathProvider;

    private final ArrayList<Integer> portList;

    public GrpcServerScalingController(Environment env, PathProvider pathProvider) {
        this.portList = new ArrayList<>();
        this.env = env;
        this.pathProvider = pathProvider;
    }

    public ArrayList<Integer> getPortList() {
        return portList;
    }

    private int findFreePort() {
        try (ServerSocket socket = new ServerSocket(0)) {
            socket.setReuseAddress(true);
            int port = socket.getLocalPort();
            try {
                socket.close();
            } catch (IOException e) {
                // Ignore IOException on close()
                System.out.println("Problem in closing socket connection");
            }
            return port;
        } catch (IOException e) {
            System.out.println("\"Could not find a free TCP/IP port to start embedded Jetty HTTP Server on\"");
        }
        throw new IllegalStateException("Could not find a free TCP/IP port to start embedded Jetty HTTP Server on");
    }

    private void getFreePortList(int requirements) {
        requirements = requirements < (Runtime.getRuntime().availableProcessors() - 2) ? requirements : 4;
        for (int i = 0; i < requirements; i++) {
            portList.add(findFreePort());
        }
    }

    public void grpcServerScaleUp(boolean isFirstInstance) {
        String envThreshold = env.getProperty("server_threshold");
        if (isFirstInstance) {
            if (envThreshold != null) {
                int requirements = Integer.parseInt(envThreshold);
                getFreePortList(requirements);
                storePortsLocally(this.portList);
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

    private void storePortsLocally(ArrayList<Integer> portList) {
        String storageDirectory = pathProvider.provideStoragePath();
        String pathForPortsFile = storageDirectory + File.separator + "port.txt";
        File localPortFile;
        FileWriter localPortFileWriter;
        try {
            localPortFile = new File(pathForPortsFile);
            if (!localPortFile.exists()) {
                localPortFile.createNewFile();
            }
            localPortFileWriter = new FileWriter(pathForPortsFile);
            StringBuilder p = new StringBuilder();
            for (int port : portList) p.append(port).append(" ");
            localPortFileWriter.write(p.toString());
            localPortFileWriter.flush();
            localPortFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void spawnGrpcServer(int port) {
        String spawnScriptPath = pathProvider.provideScriptPath() + File.separator + "spawn.py";
        String cmd = "python " + spawnScriptPath + " " + port;
        System.out.println(cmd);
        Runtime run = Runtime.getRuntime();
        try {
            Process pr = run.exec(cmd);
        } catch (IOException e) {
            System.out.println("Exception encountered while running python script for spawning gRPC workers");
        }
        System.out.println("=================================");

    }

    public void grpcServerScaleDown() {
        killGrpcServer(this.portList);
    }

    public void killGrpcServer(ArrayList<Integer> ports) {
        String spawnScriptPath = pathProvider.provideScriptPath() + File.separator + "kill.py";
        for (int port : ports) {
            String cmd = "python " + spawnScriptPath + " " + port;
            System.out.println(cmd);
            Runtime run = Runtime.getRuntime();
            try {
                Process pr = run.exec(cmd);
            } catch (IOException e) {
                System.out.println("Exception encountered while running python script for killing gRPC workers");
            }
            System.out.println("Worker gRPC on " + port + " was killed.");
            System.out.println("=================================");
        }
    }
}
