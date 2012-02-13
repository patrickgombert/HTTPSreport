package Server.src;

public interface Route {
    
    public void addEntry(String verb, String route, Class callBack);
    
    public Client getCallBack(String verb, String route);
}
