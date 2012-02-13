package Server.src.Clients.src;

import Server.src.Client;
import Server.src.Marketer;
import Server.src.Memo;

public class FourOhFourClient implements Client {

    public void setMemo(Memo memo) {

    }

    public void execute(Marketer marketer) {
        contactMarketer(marketer, new Memo(404, "HTML", "<html>Not Found</html>"));
    }

    public void contactMarketer(Marketer marketer, Memo returnMemo) {
        marketer.clientResponse(returnMemo);
    }

}