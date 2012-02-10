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
    
    @Test
    public void testGetJarDirectory() {
        String[] cmd = {"-j", "/path/to/test"};
        CommandParser parser = new CommandParser(cmd);
        assertEquals("/path/to/test", parser.getJarDirectory());
        cmd[0] = "--jar";
        parser = new CommandParser(cmd);
        assertEquals("/path/to/test", parser.getJarDirectory());
    }
    
    @Test
    public void testGetClassName() {
        String[] cmd = {"-c", "TestClass"};
        CommandParser parser = new CommandParser(cmd);
        assertEquals("TestClass", parser.getClassName());
        cmd[0] = "--class";
        parser = new CommandParser(cmd);
        assertEquals("TestClass", parser.getClassName());
    }

}
