package Server.test;

import Server.src.Clients.src.FourOhFourClient;
import Server.src.HashMapRoute;
import Server.src.mocks.src.ClientMock;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HashMapRouteTest extends TestCase {

    private HashMapRoute hashMapRoute;
    
    @Before
    public void setUp() {
        hashMapRoute = new HashMapRoute();
    }
    
    @After
    public void tearDown() {
        hashMapRoute = null;
    }


    
    @Test
    public void testAddVerb() {
        hashMapRoute.addVerb("GET");
        assertTrue(hashMapRoute.getVerbs().contains("GET"));
    }
    
    @Test
    public void testAddRoute() {
        hashMapRoute.addVerb("GET");
        hashMapRoute.addRoute("GET", "/test");
        assertTrue(hashMapRoute.getRoutes("GET").contains("/test"));
    }

    @Test
    public void testAddAndGetCallBack() {
        hashMapRoute.addVerb("GET");
        hashMapRoute.addRoute("GET", "/test");
        hashMapRoute.addCallBack("GET", "/test", ClientMock.class);
        assertEquals(ClientMock.class, hashMapRoute.getCallBack("GET", "/test").getClass());
    }

    @Test
    public void testGetVerbs() {
        hashMapRoute.addVerb("GET");
        hashMapRoute.addVerb("PUT");
        hashMapRoute.addVerb("POST");
        assertTrue(hashMapRoute.getVerbs().contains("GET"));
        assertTrue(hashMapRoute.getVerbs().contains("PUT"));
        assertTrue(hashMapRoute.getVerbs().contains("POST"));
    }

}
