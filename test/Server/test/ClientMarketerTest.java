package Server.test;

import Server.src.Client;
import Server.src.ClientMarketer;
import Server.src.Packet;
import Server.src.Route;
import Server.src.mocks.src.ClientMock;
import Server.test.mocks.src.MiddlemanMock;
import Server.test.mocks.src.TestingRoute;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientMarketerTest extends TestCase {

    ClientMarketer clientMarketer;
    
    @Before
    public void setUp() {
        clientMarketer = new ClientMarketer();
    }
    
    @After
    public void tearDown() {
        clientMarketer = null;
    }

    @Test
    public void testInvokeCallBack() {
        ClientMock client = new ClientMock();
        clientMarketer.invokeCallBack(client, null);
        assertTrue(client.setPacket);
        assertTrue(client.executed);
    }
    
    @Test
    public void testFindCallBack() {
        Route testingRoute = new TestingRoute("ClientMock");
        Client callBack = clientMarketer.findCallBack("GET", "/test", testingRoute);
        assertEquals(ClientMock.class, callBack.getClass());
    }

    @Test
    public void testClientResponse() {
        MiddlemanMock middlemanMock = new MiddlemanMock(null, null);
        clientMarketer.setExecutive(middlemanMock);
        Packet packet = new Packet(200, "HTML");
        clientMarketer.clientResponse(packet);
        assertEquals(middlemanMock.getCall(), "sendResponse");
        assertEquals(middlemanMock.getData(), packet);
    }

}
