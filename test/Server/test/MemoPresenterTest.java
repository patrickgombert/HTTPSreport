package Server.test;

import Server.src.Memo;
import Server.src.MemoPresenter;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MemoPresenterTest extends TestCase {
    
    Memo memo;
    MemoPresenter presenter;
    
    @Before
    public void setUp() {
        memo = new Memo(200, "HTML", "<html>Hello World!</html>");
        presenter = new MemoPresenter(memo);
    }

    @After
    public void tearDown() {
        memo = null;
        presenter = null;
    }
    
    @Test 
    public void testGetOutMemo() {
        String out = presenter.getOutMemo();
        assertEquals("HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Server: HTTPSreport\r\n" +
                "\r\n" +
                "<html>Hello World!</html>", out);
    }

}
    
