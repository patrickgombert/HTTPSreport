package Server.src;

public interface Marketer {
    
    public void execute(Memo memo, Route routes, Executive executive);
    
    public void invokeCallBack(Client callback, Memo memo);
    
    public void clientResponse(Memo memo);
}
