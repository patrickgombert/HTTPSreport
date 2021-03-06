package Server.test.mocks.src;

import Server.src.Client;
import Server.src.Route;
import Server.src.mocks.src.ClientMock;

public class TestingRoute implements Route {

    String findMe;
    public String call;
    public String data;
    
    public TestingRoute(String _findMe) {
        findMe = _findMe;
    }
    
    public void addEntry(String verb, String route, Class callBack) {
        call = "addEntry";
        data = verb + " " + route + " " + callBack.getSimpleName();
    }
    
    public Client getCallBack(String verb, String route) {
        return new ClientMock();
    }

}
