package Server.test;

import Server.src.CommandParser;
import junit.framework.TestCase;
import org.junit.Test;

public class CommandParserTest extends TestCase {

    @Test  
    public void testGetPortNumber() {
        String[] cmd = {"-p", "3000"};
        CommandParser parser = new CommandParser(cmd);
        assertEquals(3000, parser.getPortNumber());
        cmd[0] = "--port";
        parser = new CommandParser(cmd);
        assertEquals(3000, parser.getPortNumber());
    }

    @Test
    public void testGetRoutesFile() {
        String[] cmd = {"-r", "test"};
        CommandParser parser = new CommandParser(cmd);
        assertEquals("test", parser.getRouteFilePath());
        cmd[0] = "--routes";
        parser = new CommandParser(cmd);
        assertEquals("test", parser.getRouteFilePath());
    }

}
