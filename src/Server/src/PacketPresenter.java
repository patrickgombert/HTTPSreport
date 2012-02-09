package Server.src;

import java.util.Set;

public class PacketPresenter {
    
    Packet packet;
    
    public PacketPresenter(Packet _packet) {
        packet = _packet;    
    }
    
    public String getOutPacket() {
        String returnPacket = "";
        returnPacket += packet.getField("Version");
        returnPacket += " " + packet.getField("Status") + "\r\n";
        Set<String> keys = packet.getFields();
        keys.remove("Verb");
        keys.remove("Status");
        keys.remove("Path");
        keys.remove("Version");
        for(String key : keys) {
            if(!key.equals("Content")) {
                returnPacket += key + ": " + packet.getField(key) + "\r\n";
            }
        }
        if (keys.contains("Content")) {
            returnPacket += "\r\n" + packet.getField("Content");
        }
        return returnPacket;
    }
}
