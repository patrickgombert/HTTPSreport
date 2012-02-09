package Server.test.persistance.test;

import Server.src.persistance.src.InMemoryDB;
import Server.src.persistance.src.Persistable;
import junit.framework.TestCase;
import org.junit.Test;

public class InMemoryDBTest extends TestCase {

    @Test
    public void testGetInstance() {
        Persistable memory1 = InMemoryDB.getInstance();
        Persistable memory2 = InMemoryDB.getInstance();
        assertEquals(memory1, memory2);
    }

    @Test
    public void testReadWrite() {
        Persistable memory = InMemoryDB.getInstance();
        memory.write("test", "pass");
        assertEquals("pass", memory.read("test"));
    }

}
