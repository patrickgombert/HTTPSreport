package Server.src;

import Server.src.Clients.src.FourOhFourClient;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapRoute implements Route {
    
    private ConcurrentHashMap<String, ConcurrentHashMap<String, Class>> routeMap;
    
    public HashMapRoute() {
        routeMap = new ConcurrentHashMap<String, ConcurrentHashMap<String, Class>>();
    }
    
    public void addVerb(String verb) {
        if(!getVerbs().contains(verb)) {
            ConcurrentHashMap<String, Class> innerMap = new ConcurrentHashMap<String, Class>();
            routeMap.put(verb, innerMap);
        }
    }
    
    public Set getVerbs() {
        return routeMap.keySet();
    }
    
    public void addRoute(String verb, String route) {
        routeMap.get(verb).put(route, Object.class);
    }
    
    public Set getRoutes(String verb) {
        return routeMap.get(verb).keySet();
    }

    public void addCallBack(String verb, String route, Class callBack) {
        routeMap.get(verb).put(route, callBack);
    }

    public void addEntry(String verb, String route, Class callBack) {
        addVerb(verb);
        addRoute(verb, route);
        addCallBack(verb, route, callBack);
    }

    public synchronized Client getCallBack(String verb, String route) {
        try {
            Class clientImpl = routeMap.get(verb).get(route);
            Client returnClient = findCallBack(clientImpl);
            return returnClient;
        } catch(NullPointerException e) {
            return null;
        }
    }

    private Client findCallBack(Class clientImpl) {
        try{
            return (Client)clientImpl.newInstance();
        } catch(Exception e) {
            return new FourOhFourClient();
        }
    }

}
