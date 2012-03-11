package Server.src;

import java.io.*;
import java.net.ServerSocket;

public class Receptionist {

    public static void main(String[] args) {
        CommandParser commandparser = new CommandParser(args);
        int port = commandparser.getPortNumber();
        String routeFile = commandparser.getRouteFilePath();
        String jarDirectory = commandparser.getJarDirectory();
        new Receptionist(new Doorman(port, routeFile, jarDirectory)).run();
    }

    private ServerSocket serverSocket;
    private Route routes;

    public Receptionist(Doorman doorman) {
        try {
            serverSocket = doorman.generateServerSocket();
            routes = doorman.generateRoutes();
        } catch(IOException e) {
            System.err.println("Failed to bind to the specified port");
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

//    private Class generateClass(String jarDir, String className) {
//        JarClassLoader jarLoader = new JarClassLoader(jarDir);
//        try {
//            Class clientImpl = jarLoader.loadClass(className, true);
//            return clientImpl;
//        } catch(Exception e) {
//            System.err.println("Couldn't find the specified client class");
//            System.exit(1);
//            return null;
//        }
//    }
//
//    private void generateRoutes(String filePath, Class clientImpl) throws IllegalArgumentException {
//        try {
//            File file = new File(filePath);
//            Scanner scanner = new Scanner(file);
//            FileRouteScanner routeScanner = new FileRouteScanner(scanner, routes, clientImpl);
//            routeScanner.reportByLine();
//        } catch(Exception e) {
//            throw new IllegalArgumentException();
//        }
//    }

}