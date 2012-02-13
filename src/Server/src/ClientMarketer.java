package Server.src;

public class ClientMarketer implements Marketer {

    private Executive executive;
    
    public void setExecutive(Executive _executive) {
        executive = _executive;
    }

    public void execute(Memo memo, Route routes, Executive _executive) {
        executive = _executive;
        String verb = memo.getField("Verb");
        String path = memo.getField("Path");
        Client client = findCallBack(verb, path, routes);
        if (client != null) {
            invokeCallBack(client, memo);
        }  else {
            clientResponse(new Memo(404, "HTML", "<html>Not Found</html>"));
        }
    }

    public Client findCallBack(String verb, String path, Route route) {
        try {
            return route.getCallBack(verb, path);
        } catch(Exception e) {
            System.err.println("Incoming route " + route + " not found");
            clientResponse(new Memo(404, "HTML"));
            return null;
        }
    }

    public void invokeCallBack(Client client, Memo memo) {
        try {
            client.execute(this, memo);
        } catch(Exception e) {
            System.err.println("Callback of client failed");
            clientResponse(new Memo(500, "HTML"));
        }
    }

    public void clientResponse(Memo memo) {
        executive.sendResponse(memo);
    }

}
