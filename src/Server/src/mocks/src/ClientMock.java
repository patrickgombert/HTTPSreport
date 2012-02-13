package Server.src.mocks.src;

import Server.src.Client;
import Server.src.Marketer;
import Server.src.Memo;

public class ClientMock implements Client {

    public boolean executed;
    public boolean setMemo;

    public ClientMock() {
        executed = false;
        setMemo = false;
    }

    public void setMemo(Memo memo) {
        setMemo = true;
    }

    public void execute(Marketer marketer) {
        executed = true;
    }
    
    public void contactMarketer(Marketer marketer, Memo memo) {

    }

}
