package Server.test;

import Server.src.Packet;
import Server.src.TimeClient;
import Server.test.mocks.src.TestMarketer;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TimeClientTest extends TestCase {

    TimeClient timeClient;
    
    private String[] getTimeZones() {
        String[] timeZones = {"Chicago", "Ohio"};
        return timeZones;
    }
    
    @Before
    public void setUp() {
        timeClient = new TimeClient();
    }

    @After
    public void tearDown() {
        timeClient = null;
    }

    @Test
    public void testPacket() {
        Packet packet = new Packet(200, "HTML");
        timeClient.setPacket(packet);
        assertEquals(packet, timeClient.getPacket());
    }

    @Test
    public void testContactMarketer() {
        Packet packet = new Packet(200, "HTML");
        TestMarketer testMarketer = new TestMarketer();
        timeClient.contactMarketer(testMarketer, packet);
        assertEquals("clientResponse", testMarketer.lastCall());
        assertEquals(packet, testMarketer.lastData());
    }

    @Test
    public void testTopLevelTimeZones() {
        String response = timeClient.findTime("/time");
        String[] timeZones = getTimeZones();
        for(String timeZone : timeZones) {
            assertTrue(response.contains(timeZone + " Time"));
            assertTrue(response.contains("/time/" + timeZone));
        }
    }
    
    @Test
    public void testTimeZones() {
        for(String timeZone : getTimeZones()) {
            String response = timeClient.findTime("/time/" + timeZone);
            assertTrue(response.contains(":"));
        }
    }

}
