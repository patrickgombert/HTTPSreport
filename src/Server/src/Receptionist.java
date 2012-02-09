package Server.src;

import java.io.*;
import java.net.ServerSocket;
import java.util.Scanner;

public class Receptionist {

    public static void main(String[] args) {
        CommandParser commandparser = new CommandParser(args);
        new Receptionist(commandparser.getPortNumber(), commandparser.getRouteFilePath()).run();
    }

    private ServerSocket serverSocket;
    private Route routes;

    public Receptionist(int listenPort, String routesFile) {
        try {
            serverSocket = new ServerSocket(listenPort);
            routes = new HashMapRoute();
            generateRoutes(routesFile);
        } catch(IOException e) {
            System.err.println("Failed to bind to port: " + listenPort);
        } catch(IllegalArgumentException e) {
            System.err.println("Failed to read routes file");
        }
    }

    public void run() {
        while(true) {
            try {
                new Executive(serverSocket.accept(), new ClientMarketer(), routes).start();
            } catch(IOException e) {
                System.err.println("Dropped the ball on an incoming connection");
            }
        }
    }

    private void generateRoutes(String filePath) throws IllegalArgumentException {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            FileRouteScanner routeScanner = new FileRouteScanner(scanner, routes);
            routeScanner.reportByLine();
        } catch(Exception e) {
            throw new IllegalArgumentException();
        }
    }

}