package Server.test.mocks.src;

import Server.src.Executive;
import Server.src.Marketer;
import Server.src.Memo;

import java.net.Socket;

public class MiddlemanMock extends Executive {
    
    private String call;
    private Object data;

    public MiddlemanMock(Socket socket, Marketer marketer) {
        super(socket, marketer, null);
    }
    
    public String getCall() {
        return call;
    }
    
    public Object getData() {
        return data;
    }
    
    public void sendResponse(Memo memo) {
        call = "sendResponse";
        data = memo;
    }

}
