package Server.test.Clients.test;

import Server.src.Clients.src.TicTacToeClient;
import Server.src.Packet;
import Server.test.mocks.src.TestMarketer;
import junit.framework.TestCase;
import org.junit.*;

public class TicTacToeClientTest extends TestCase {

    TicTacToeClient ticTacToeClient;

    @Before
    public void setUp() {
        ticTacToeClient = new TicTacToeClient();
    }

    @After
    public void tearDown() {
        ticTacToeClient = null;
    }

    @Test
    public void testPacket() {
        Packet packet = new Packet(200, "HTML");
        ticTacToeClient.setPacket(packet);
        assertEquals(packet, ticTacToeClient.getPacket());
    }

    @Test
    public void testContactMarketer() {
        Packet packet = new Packet(200, "HTML");
        TestMarketer testMarketer = new TestMarketer();
        ticTacToeClient.contactMarketer(testMarketer, packet);
        assertEquals("clientResponse", testMarketer.lastCall());
        assertEquals(packet, testMarketer.lastData());
    }  
    
    @Test
    public void testRetrievePath() {
        Packet packet = new Packet(200, "HTML");
        packet.setField("Path", "/test");
        ticTacToeClient.setPacket(packet);
        assertEquals("/test", ticTacToeClient.retrievePath());
    }
    
    @Test
    public void testRetrieveCookie() {
        Packet packet = new Packet(200, "HTML");
        packet.setField("Cookie", "testing");
        ticTacToeClient.setPacket(packet);
        assertEquals("testing", ticTacToeClient.retrieveCookie());
    }

}