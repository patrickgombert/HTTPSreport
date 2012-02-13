package Server.src;

public interface Client {

    public void setMemo(Memo memo);

    public void execute(Marketer marketer);
    
    public void contactMarketer(Marketer marketer, Memo returnMemo);

}
