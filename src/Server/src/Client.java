package Server.src;

public abstract class Client {

    public abstract void execute(Marketer marketer, Memo inMemo);
    
    public void contactMarketer(Marketer marketer, Memo returnMemo) {
        marketer.clientResponse(returnMemo);
    }

}
