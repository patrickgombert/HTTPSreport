package Server.src;

public interface Client {

    public void setPacket(Packet packet);

    public void execute(Marketer marketer);
    
    public void contactMarketer(Marketer marketer, Packet returnPacket);

}
