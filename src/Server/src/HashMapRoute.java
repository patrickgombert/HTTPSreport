package Server.src;

import Server.src.mocks.src.ClientMock;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapRoute implements Route {
    
    private ConcurrentHashMap<String, ConcurrentHashMap<String, String>> routeMap;
    
    public HashMapRoute() {
        routeMap = new ConcurrentHashMap<String, ConcurrentHashMap<String, String>>();
        addVerb("GET");
        addVerb("POST");
        addVerb("PUT");
        addVerb("DELETE");
        addVerb("HEAD");
    }
    
    public void addVerb(String verb) {
        if(!getVerbs().contains(verb)) {
            ConcurrentHashMap<String, String> innerMap = new ConcurrentHashMap<String, String>();
            routeMap.put(verb, innerMap);
        }
    }
    
    public Set getVerbs() {
        return routeMap.keySet();
    }
    
    public void addRoute(String verb, String route) {
        routeMap.get(verb).put(route, "");
    }
    
    public Set getRoutes(String verb) {
        return routeMap.get(verb).keySet();
    }

    public void addCallBack(String verb, String route, String callback) {
        routeMap.get(verb).put(route, callback);
    }

    public void addEntry(String verb, String route, String callback) {
        addVerb(verb);
        addRoute(verb, route);
        addCallBack(verb, route, callback);
    }

    public synchronized Client getCallBack(String verb, String route) {
        try {
            String object = routeMap.get(verb).get(route);
            Client returnClient = findCallBack(object);
            return returnClient;
        } catch(NullPointerException e) {
            return null;
        }
    }

    private Client findCallBack(String callBack) {
        if(callBack.equals("SanityClient"))
            return new SanityClient();
        if(callBack.equals("TicTacToeClient"))
            return new TicTacToeClient();
        if(callBack.equals("TimeClient"))
            return new TimeClient();
        if(callBack.equals("ClientMock"))
            return new ClientMock();
        return new FourOhFourClient();
    }

}
