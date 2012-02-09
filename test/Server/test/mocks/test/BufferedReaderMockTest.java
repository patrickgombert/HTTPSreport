package Server.test.mocks.test;

import Server.test.mocks.src.BufferedReaderMock;
import junit.framework.TestCase;
import org.junit.*;

public class BufferedReaderMockTest extends TestCase {
    
    BufferedReaderMock bufferedReader;

    @After
    public void tearDown() {
        bufferedReader = null;
    }
    
    @Test
    public void testReadEmptyString() {
        bufferedReader = new BufferedReaderMock("");
        assertEquals(null, bufferedReader.readLine());
    }
    
    @Test
    public void testReadSingleLine() {
        bufferedReader = new BufferedReaderMock("Line\r\n");
        assertEquals("Line", bufferedReader.readLine());
    }
    
    @Test 
    public void testReadMultipleLines() {
        bufferedReader = new BufferedReaderMock("Line1\r\nLine2\r\nLine3\r\n");
        assertEquals("Line1", bufferedReader.readLine());
        assertEquals("Line2", bufferedReader.readLine());
        assertEquals("Line3", bufferedReader.readLine());
    }
    
}
