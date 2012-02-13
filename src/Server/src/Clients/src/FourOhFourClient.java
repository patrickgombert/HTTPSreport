package Server.src.Clients.src;

import Server.src.Client;
import Server.src.Marketer;
import Server.src.Memo;

public class FourOhFourClient extends Client {

    public void execute(Marketer marketer, Memo inMemo) {
        contactMarketer(marketer, new Memo(404, "HTML", "<html>Not Found</html>"));
    }

}