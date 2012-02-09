package Server.test;

import Server.src.FileRouteScanner;
import Server.test.mocks.src.TestingRoute;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

public class FileRouteScannerTest extends TestCase {

    FileRouteScanner scanner;
    TestingRoute route;

    @Before
    public void setUp() {
        route = new TestingRoute("MockClient");
        scanner = new FileRouteScanner(new Scanner("GET /test ClientMock"), route);
    }

    @After
    public void tearDown() {
        route = null;
        scanner = null;
    }

    @Test
    public void testInstantiateWithStringAndRoute() {
        assertNotNull(scanner);
    }

    @Test
    public void testResponses() {
        scanner.reportByLine();
        assertEquals("addEntry", route.call);
        assertEquals("GET /test ClientMock", route.data);
    }

}
