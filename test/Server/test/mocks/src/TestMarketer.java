package Server.test.mocks.src;

import Server.src.*;

public class TestMarketer implements Marketer {
    
    private String call;
    private Object data;

    public void execute(Memo memo, Route hashMapRoute, Executive executive) {
        call = "execute";
        data = memo;
    }
    
    public void invokeCallBack(Client client, Memo memo) {

    }
    
    public String lastCall() {
        return call;
    }
    
    public Object lastData() {
        return data;
    }
    
    public void clientResponse(Memo memo) {
        call = "clientResponse";
        data = memo;
    }
}
