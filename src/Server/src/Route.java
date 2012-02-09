package Server.src;

public interface Route {
    
    public void addEntry(String verb, String route, String callback);
    
    public Client getCallBack(String verb, String route);
}
