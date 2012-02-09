package Server.src.Clients.src;

import Server.src.Client;
import Server.src.Marketer;
import Server.src.Packet;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeClient implements Client {
    
    Packet packet;
    
    public TimeClient() {
        packet = null;
    }
    
    public void setPacket(Packet _packet) {
        packet = _packet;
    }
    
    public Packet getPacket() {
        return packet;
    }
    
    public void execute(Marketer marketer) {
        Packet outPacket = new Packet(200, "HTML", "<html>" + findTime(packet.getField("Path")) + "</html>");
        contactMarketer(marketer, outPacket);
    }

    public void contactMarketer(Marketer marketer, Packet packet) {
        marketer.clientResponse(packet);
    }
    
    public String findTime(String timeZone) {
        Calendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        if(timeZone.endsWith("/time")) {
            return "<a href=\"/time/Chicago\">Chicago Time</a><br>\n" +
                   "<a href=\"/time/Ohio\">Ohio Time</a><br>\n";
        } else if(timeZone.endsWith("Chicago")) {
            // Time is current
        } else if(timeZone.endsWith("Ohio")) {
            hour += 1;
        }
        if(minute < 10) {
            return hour + ":" + "0" + minute;
        } else {
            return hour + ":" + minute;
        }
    }
}
