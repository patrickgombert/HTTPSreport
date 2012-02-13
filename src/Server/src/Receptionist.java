package Server.src;

import jar.classLoader.JarClassLoader;

import java.io.*;
import java.net.ServerSocket;
import java.util.Scanner;

public class Receptionist {

    public void checkInputValidity(String jarDir, String className) {
        if(jarDir == null || className == null) {
            System.err.println("Requires a jar with a class that implements Client");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        CommandParser commandparser = new CommandParser(args);
        int port = commandparser.getPortNumber();
        String routeFile = commandparser.getRouteFilePath();
        String jarDirectory = commandparser.getJarDirectory();
        String className = commandparser.getClassName();
        new Receptionist(port, routeFile, jarDirectory, className).run();
    }

    private ServerSocket serverSocket;
    private Route routes;

    public Receptionist(int listenPort, String routesFile, String jarDir, String className) {
        checkInputValidity(jarDir,  className);
        try {
            serverSocket = new ServerSocket(listenPort);
            Class clientImpl = generateClass(jarDir, className);
            routes = new HashMapRoute();
            generateRoutes(routesFile, clientImpl);
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
    
    private Class generateClass(String jarDir, String className) {
        JarClassLoader jarLoader = new JarClassLoader(jarDir);
        try {
            Class clientImpl = jarLoader.loadClass(className, true);
            return clientImpl;
        } catch(Exception e) {
            System.err.println("Couldn't find the specified client class");
            System.exit(1);
            return null;
        }
    }

    private void generateRoutes(String filePath, Class clientImpl) throws IllegalArgumentException {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            FileRouteScanner routeScanner = new FileRouteScanner(scanner, routes, clientImpl);
            routeScanner.reportByLine();
        } catch(Exception e) {
            throw new IllegalArgumentException();
        }
    }

}