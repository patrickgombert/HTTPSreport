package Server.test.mocks.src;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SocketMock extends Socket {
    
    private ArrayList<String> callList;
    
    public SocketMock() {
        callList = new ArrayList<String>();
    }

    public InputStream getInputStream() {
        callList.add("getInputStream");
        return new InputStreamMock();
    }
    
    public OutputStream getOutputStream() {
        callList.add("getOutputStream");
        return new OutputStreamMock();
    }

    public void close() {
        callList.add("close");
    }
    
    public String lastCall() {
        return callList.get(callList.size() - 1);
    }
    
    public boolean called(String call) {
        return callList.contains(call);
    }
    
    class InputStreamMock extends InputStream {

        public int read() {
            return -1;
        }
    }
    
    class OutputStreamMock extends OutputStream {

        public void write(int i) {

        }
    }
}
