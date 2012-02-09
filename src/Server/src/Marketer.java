package Server.src;

public interface Marketer {
    
    public void execute(Packet packet, Route routes, Executive executive);
    
    public void invokeCallBack(Client callback, Packet packet);
    
    public void clientResponse(Packet packet);
}
