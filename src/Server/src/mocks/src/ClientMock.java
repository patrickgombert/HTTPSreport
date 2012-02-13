package Server.src.mocks.src;

import Server.src.Client;
import Server.src.Marketer;
import Server.src.Memo;

public class ClientMock extends Client {

    public boolean executed;
    public boolean setMemo;

    public ClientMock() {
        executed = false;
        setMemo = false;
    }

    public void execute(Marketer marketer, Memo inMemo) {
        setMemo = true;
        executed = true;
    }

}
