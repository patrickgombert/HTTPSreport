package Server.src;

public class ClientMarketer implements Marketer {

    private Executive executive;
    
    public void setExecutive(Executive _executive) {
        executive = _executive;
    }

    public void execute(Packet packet, Route routes, Executive _executive) {
        executive = _executive;
        String verb = packet.getField("Verb");
        String path = packet.getField("Path");
        Client client = findCallBack(verb, path, routes);
        if (client != null) {
            invokeCallBack(client, packet);
        }  else {
            clientResponse(new Packet(404, "HTML", "<html>Not Found</html>"));
        }
    }

    public Client findCallBack(String verb, String path, Route route) {
        try {
            return route.getCallBack(verb, path);
        } catch(Exception e) {
            System.err.println("Incoming route " + route + " not found");
            clientResponse(new Packet(404, "HTML"));
            return null;
        }
    }

    public void invokeCallBack(Client client, Packet packet) {
        try {
            client.setPacket(packet);
            client.execute(this);
        } catch(Exception e) {
            System.err.println("Callback of client failed");
            clientResponse(new Packet(500, "HTML"));
        }
    }

    public void clientResponse(Packet packet) {
        executive.sendResponse(packet);
    }

}
