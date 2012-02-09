package Server.src;

import java.util.Scanner;

public class FileRouteScanner {
    
    Scanner scanner;
    Route routes;
    
    public FileRouteScanner(Scanner _scanner, Route _routes) {
        scanner = _scanner;
        routes = _routes;
    }

    public void reportByLine() throws IllegalArgumentException {
        try {
            while(scanner.hasNextLine()) {
                String[] line = processLine(scanner.nextLine());
                if(line.length != 3) {
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
