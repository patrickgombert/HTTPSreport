package Server.test;

import Server.src.Packet;
import junit.framework.TestCase;
import org.junit.*;

import java.util.Set;

public class PacketTest extends TestCase {

    Packet packet;

    @Before
    public void setUp() {
        packet = new Packet(200, "JSON", "{\"test\":true}");
    }

    @After
    public void tearDown() {
        packet = null;
    }

    @Test
    public void testGetField() {
        assertEquals("200 OK", packet.getField("Status"));
        assertEquals("application/json", packet.getField("Content-Type"));
        assertEquals("HTTP/1.1", packet.getField("Version"));
        assertEquals("{\"test\":true}", packet.getField("Content"));
        assertNull(packet.getField("foobar"));
    }
    
    @Test
    public void testGetFields() {
        Set<String> keys = packet.getFields();
        assertTrue(keys.contains("Status"));
        assertTrue(keys.contains("Content-Type"));
        assertTrue(keys.contains("Version"));
        assertTrue(keys.contains("Content"));
        assertFalse(keys.contains("foobar"));
    }

    @Test
    public void testSetField() {
        packet.setField("Test", "Pass");
        assertEquals("Pass", packet.getField("Test"));
    }

    @Test
    public void testRemoveField() {
        packet.removeField("Status");
        assertNull(packet.getField("Status"));
    }

}