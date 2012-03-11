package Server.src;

import java.util.ArrayList;
import java.util.Scanner;

public class FileRouteScanner {

    private ArrayList<String> verbs;

    private Scanner scanner;
    
    public FileRouteScanner(Scanner _scanner) {
        setVerbs();
        scanner = _scanner;
    }
    
    private void setVerbs() {
        verbs = new ArrayList<String>();
        verbs.add("GET");
        verbs.add("PUT");
        verbs.add("POST");
        verbs.add("DELETE");
        verbs.add("HEAD");
    }

    public String[] report() throws IllegalArgumentException {
        String[] line = processLine(scanner.nextLine());
        if(line.length != 3 || !verbs.contains(line[0])) {
            throw new IllegalArgumentException();
        }
        return line;
    }
    
    public boolean ready() {
        if (scanner.hasNextLine()) {
            return true;
        } else {
            scanner.close();
            return false;
        }
    }
    
    private String[] processLine(String line) {
        return line.split(" ");
    }
}
