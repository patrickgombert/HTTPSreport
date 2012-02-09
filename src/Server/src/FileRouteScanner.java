package Server.src;

import java.util.ArrayList;
import java.util.Scanner;

public class FileRouteScanner {

    private ArrayList<String> verbs;

    private Scanner scanner;
    private Route routes;
    
    public FileRouteScanner(Scanner _scanner, Route _routes) {
        setVerbs();
        scanner = _scanner;
        routes = _routes;
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
                if(line.length != 3 || !verbs.contains(line[0])) {
                    throw new IllegalArgumentException();
                }
                routes.addEntry(line[0], line[1], line[2]);
            }
        } finally {
            scanner.close();
        }
    }
    
    private String[] processLine(String line) {
        return line.split(" ");
    }
}
