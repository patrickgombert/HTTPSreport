package Server.test;

import Server.src.FileRouteScanner;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

public class FileRouteScannerTest extends TestCase {

    FileRouteScanner scanner;

    @Before
    public void setUp() {
        scanner = new FileRouteScanner(new Scanner("GET /test ClientMock"));
    }

    @After
    public void tearDown() {
        scanner = null;
    }

    @Test
    public void testInstantiateWithString() {
        assertNotNull(scanner);
    }
    
    @Test
    public void testReady() {
        assertTrue(scanner.ready());
        scanner.report();
        assertFalse(scanner.ready());
    }

    @Test
    public void testResponses() {
        String[] response = scanner.report();
        assertEquals("GET", response[0]);
        assertEquals("/test", response[1]);
        assertEquals("ClientMock", response[2]);
    }

    @Test
    public void testInvalidHTTPVerb() {
        scanner = new FileRouteScanner(new Scanner("DERP /test ClientMock"));
        try {
            scanner.report();
            fail("Didn't throw IllegalArgumentException");
        } catch(Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

}
