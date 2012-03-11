package Server.src;

import jar.classLoader.JarClassLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class Doorman {

    private int port;
    private String routeFile;
    private JarClassLoader jarLoader;
    
    public Doorman(int _port, String _routeFile, String _jarDirectory) {
        port = _port;
        routeFile = _routeFile;
        jarLoader = new JarClassLoader(_jarDirectory);
    }

    public ServerSocket generateServerSocket() throws IOException {
        return new ServerSocket(port);
    }
    
    public Route generateRoutes() throws FileNotFoundException {
        Route routes = new HashMapRoute();
        File file = new File(routeFile);
        FileRouteScanner scanner = new FileRouteScanner(new Scanner(file));
        while(scanner.ready()) {
           String[] line = scanner.report();
           Class classImpl = clientImpl(line[2]);
           routes.addEntry(line[0], line[1], classImpl);
        }
        return routes;
    }
    
    private Class clientImpl(String className) {
        try {
            Class clientImpl = jarLoader.loadClass(className, true);
            return clientImpl;
        } catch(Exception e) {
            System.err.println("Couldn't find the specified client class");
            System.exit(1);
            return null;
        }
    }

}
