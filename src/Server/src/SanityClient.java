package Server.src;

public class SanityClient implements Client {

    public void setPacket(Packet packet) {

    }
    
    public void execute(Marketer marketer) {
        Packet outPacket = new Packet(200, "HTML", "<html>Hello Sanity!</html>");
        contactMarketer(marketer, outPacket);
    }
    
    public void contactMarketer(Marketer marketer, Packet returnPacket) {
        marketer.clientResponse(returnPacket);
    }
}
