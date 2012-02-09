package Server.src.Clients.src;

import Server.src.Client;
import Server.src.Marketer;
import Server.src.Packet;

public class FourOhFourClient implements Client {

    public void setPacket(Packet packet) {

    }

    public void execute(Marketer marketer) {
        contactMarketer(marketer, new Packet(404, "HTML", "<html>Not Found</html>"));
    }

    public void contactMarketer(Marketer marketer, Packet returnPacket) {
        marketer.clientResponse(returnPacket);
    }

}