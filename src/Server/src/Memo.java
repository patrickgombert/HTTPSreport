package Server.src;

import java.util.HashMap;
import java.util.Set;

public class Memo {

    private HashMap<String, String> fields = new HashMap<String, String>();

    public Memo(int status, String format) {
        fields.put("Status", generateStatus(status));
        fields.put("Content-Type", generateFormat(format));
        fields.put("Version", "HTTP/1.1");
        fields.put("Server", "Patrick's Server");
    }

    public Memo(int status, String format, String content) {
        this(status, format);
        fields.put("Content", content);
    }

    public Memo(HashMap<String, String> _fields) {
        fields = _fields;
    }

    public String getField(String field) {
        return fields.get(field);
    }
    
    public Set<String> getFields() {
        return fields.keySet();
    }

    public void setField(String field, String argument) {
        fields.put(field, argument);
    }

    public void removeField(String field) {
        fields.remove(field);
    }

    private static String generateStatus(int status) {
        switch (status) {
            case 100:
                return "100 Continue";
            case 200:
                return "200 OK";
            case 201:
                return "201 Created";
            case 304:
                return "304 Not Modified";
            case 400:
                return "400 Bad Request";
            case 401:
                return "401 Unauthorized";
            case 403:
                return "403 Forbidden";
            case 404:
                return "404 Not Found";
            case 500:
                return "500 Internal Server Error";
            case 501:
                return "501 Not Implemented";
            case 503:
                return "503 Service Unavailable";
        }

        System.err.println("Incorrect status return: " + status);
        return status + " Unknown Status";
    }

    private static String generateFormat(String format) {
        if(format.equals("JSON")) {
            return "application/json";
        } else if(format.equals("XML")) {
            return "application/xml";
        } else if(format.equals("TEXT")) {
            return "plain/text";
        } else {
            return "text/html";
        }
    }
}