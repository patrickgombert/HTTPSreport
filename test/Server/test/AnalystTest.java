package Server.test;

import Server.src.Packet;
import Server.src.Analyst;
import junit.framework.TestCase;
import org.junit.*;

public class AnalystTest extends TestCase {

    Analyst parser;

    @Before
    public void setUp() {
        parser = new Analyst("GET /test HTTP/1.1\r\n" +
                             "Host: localhost:3000\r\n" +
                             "User-Agent: Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.2.17) Gecko/20110422 Ubuntu/10.04 (lucid) Firefox/3.6.17\r\n" +
                             "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" +
                             "Accept-Language: en-us,en;q=0.5\r\n" +
                             "Accept-Encoding: gzip,deflate\r\n" +
                             "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7\r\n" +
                             "Keep-Alive: 115\r\n" +
                             "Connection: keep-alive\r\n" +
                             "\r\n" +
                             "This is data\r\n" +
                             "This, too, is data");
        parser.parse();
    }

    @Test
    public void testInstantiation() {
        assertNotNull(parser);
    }

    @Test
    public void testGetPacket() {
        assertTrue(parser.getPacket().getClass().equals(Packet.class));
    }
    
    @Test
    public void testPacketAttributes() {
        assertEquals("GET", parser.getPacket().getField("Verb"));
        assertEquals("/test", parser.getPacket().getField("Path"));
        assertEquals("keep-alive", parser.getPacket().getField("Connection"));
    }

    @Test
    public void testGetContent() {
        assertEquals("This is data\r\nThis, too, is data\r\n", parser.getPacket().getField("Content"));
    }

}