package Server.src;

import java.io.*;
import java.net.ServerSocket;
import java.util.Scanner;

public class Receptionist {

    public static void main(String[] args) {
        try {
            new Receptionist(Integer.parseInt(args[0])).run();
        } catch (Exception e) {
            new Receptionist(8080).run();
        }
    }

    private ServerSocket serverSocket;
    private Route routes;

    public Receptionist(int listenPort) {
        try {
            serverSocket = new ServerSocket(listenPort);
            routes = new HashMapRoute();
            generateRoutes("routes.txt");
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