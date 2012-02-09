package Server.src.mocks.src;

import Server.src.Client;
import Server.src.Marketer;
import Server.src.Packet;

public class ClientMock implements Client {

    public boolean executed;
    public boolean setPacket;

    public ClientMock() {
        executed = false;
        setPacket = false;
    }

    public void setPacket(Packet packet) {
        setPacket = true;
    }

    public void execute(Marketer marketer) {
        executed = true;
    }
    
    public void contactMarketer(Marketer marketer, Packet packet) {

    }

}
