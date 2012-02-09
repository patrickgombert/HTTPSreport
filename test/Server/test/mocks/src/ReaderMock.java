package Server.test.mocks.src;

import java.io.Reader;

public class ReaderMock extends Reader {

    public void close() {

    }


    public void mark() {

    }

    public int read() {
        return 0;
    }

    public int read(char[] cbuf) {
        return 0;
    }

    public int read(char[] cbuf, int off, int len) {
        return 0;
    }

    public boolean ready() {
        return true;
    }

    public void reset() {

    }

    public long skip(long n) {
        return 0;
    }
}