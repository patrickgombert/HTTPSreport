package Server.test;

import Server.src.Packet;
import Server.src.PacketPresenter;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PacketPresenterTest extends TestCase {
    
    Packet packet;
    PacketPresenter presenter;
    
    @Before
    public void setUp() {
        packet = new Packet(200, "HTML", "<html>Hello World!</html>");
        presenter = new PacketPresenter(packet);
    }

    @After
    public void tearDown() {
        packet = null;
        presenter = null;
    }
    
    @Test 
    public void testGetOutPacket() {
        String out = presenter.getOutPacket();
        assertEquals("HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Server: Patrick's Server\r\n" +
                "\r\n" +
                "<html>Hello World!</html>", out);
    }

}
    
