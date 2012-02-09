package Server.test.mocks.src;

import Server.src.*;

public class TestMarketer implements Marketer {
    
    private String call;
    private Object data;

    public void execute(Packet packet, Route hashMapRoute, Executive executive) {
        call = "execute";
        data = packet;
    }
    
    public void invokeCallBack(Client client, Packet packet) {

    }
    
    public String lastCall() {
        return call;
    }
    
    public Object lastData() {
        return data;
    }
    
    public void clientResponse(Packet packet) {
        call = "clientResponse";
        data = packet;
    }
}
