package Server.test;

import Server.src.Memo;
import junit.framework.TestCase;
import org.junit.*;

import java.util.Set;

public class MemoTest extends TestCase {

    Memo memo;

    @Before
    public void setUp() {
        memo = new Memo(200, "JSON", "{\"test\":true}");
    }

    @After
    public void tearDown() {
        memo = null;
    }

    @Test
    public void testGetField() {
        assertEquals("200 OK", memo.getField("Status"));
        assertEquals("application/json", memo.getField("Content-Type"));
        assertEquals("HTTP/1.1", memo.getField("Version"));
        assertEquals("{\"test\":true}", memo.getField("Content"));
        assertNull(memo.getField("foobar"));
    }
    
    @Test
    public void testGetFields() {
        Set<String> keys = memo.getFields();
        assertTrue(keys.contains("Status"));
        assertTrue(keys.contains("Content-Type"));
        assertTrue(keys.contains("Version"));
        assertTrue(keys.contains("Content"));
        assertFalse(keys.contains("foobar"));
    }

    @Test
    public void testSetField() {
        memo.setField("Test", "Pass");
        assertEquals("Pass", memo.getField("Test"));
    }

    @Test
    public void testRemoveField() {
        memo.removeField("Status");
        assertNull(memo.getField("Status"));
    }

}