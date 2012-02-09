package Server.test.mocks.src;

import java.io.BufferedReader;

public class BufferedReaderMock extends BufferedReader {

    private String stream;
    int position;

    public BufferedReaderMock(String _stream) {
        super(new ReaderMock());
        stream = _stream;
        position = 0;
    }

    public String readLine() {
        try {
            int index = stream.indexOf("\r\n", position);
            String returnVal = stream.substring(position, index);
            position = index + 2;
            return returnVal;
        } catch(StringIndexOutOfBoundsException e) {
            return null;
        }
    }

}
