package Server.src;

import java.util.ArrayList;
import java.util.Scanner;

public class FileRouteScanner {

    private ArrayList<String> verbs;

    private Scanner scanner;
    private Route routes;
    private Class clientImpl;
    
    public FileRouteScanner(Scanner _scanner, Route _routes, Class _clientImpl) {
        setVerbs();
        scanner = _scanner;
        routes = _routes;
        clientImpl = _clientImpl;
    }
    
    private void setVerbs() {
        verbs = new ArrayList<String>();
        verbs.add("GET");
        verbs.add("PUT");
        verbs.add("POST");
        verbs.add("DELETE");
        verbs.add("HEAD");
    }

    public void reportByLine() throws IllegalArgumentException {
        try {
            while(scanner.hasNextLine()) {
                String[] line = processLine(scanner.nextLine());
                if(line.length != 2 || !verbs.contains(line[0])) {
                    throw new IllegalArgumentException();
                }
                routes.addEntry(line[0], line[1], clientImpl);
            }
        } catch(NullPointerException e) {
            throw new IllegalArgumentException();
        } finally {
            scanner.close();
        }
    }
    
    private String[] processLine(String line) {
        return line.split(" ");
    }
}
