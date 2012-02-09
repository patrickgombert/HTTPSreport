package Server.test;

import Server.src.Executive;
import Server.test.mocks.src.TestMarketer;
import Server.test.mocks.src.SocketMock;
import Server.src.Packet;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExecutiveTest extends TestCase {
    
    Executive executive;
    SocketMock socket;
    TestMarketer testMarketer;
    
    @Before
    public void setUp() {
        testMarketer = new TestMarketer();
        socket = new SocketMock();
        executive = new Executive(socket, testMarketer, null);
    }
    
    @After 
    public void tearDown() {
        testMarketer = null;
        socket = null;
        executive = null;
    }

    @Test
    public void testInvokeMarketer() {
        Packet packet = new Packet(200, "HTML");
        executive.invokeMarketer(packet);
        assertEquals("execute", testMarketer.lastCall());
        assertEquals(packet, testMarketer.lastData());
    }

    @Test
    public void testSendResponse() {
        executive.sendResponse(new Packet(200, "HTML"));
        assertTrue(socket.called("getOutputStream"));
    }

    @Test
    public void testCloseSocketForSendResponse() {
        executive.sendResponse(new Packet(200, "HTML"));
        assertEquals("close", socket.lastCall());
    }

}
