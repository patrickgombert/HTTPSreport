package Server.src;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Executive extends Thread {
    
    private Socket clientSocket;
    private Marketer marketer;
    private Route routes;

    public Executive(Socket _clientSocket, Marketer _marketer, Route _routes) {
        clientSocket = _clientSocket;
        marketer = _marketer;
        routes = _routes;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Memo inMemo = parseMemo(in);
            if (inMemo != null) {
                invokeMarketer(inMemo);
            } else {
                closeSocket();
            }
        } catch(IOException e) {
            sendResponse(new Memo(500, "HTML", "<html>Whoops, something went wrong</html>"));
            System.err.println("Did not properly read Memo from: " + clientSocket.getInetAddress().getAddress());
        }
    }

    public Memo parseMemo(BufferedReader in) {
        Analyst parser = new Analyst(in);
        parser.parse();
        return parser.getMemo();
    }
    
    public void invokeMarketer(Memo inMemo) {
        marketer.execute(inMemo, routes, this);
    }

    public void sendResponse(Memo memo) {
        try {
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            MemoPresenter presenter = new MemoPresenter(memo);
            out.write(presenter.getOutMemo().getBytes());
            closeSocket();
        } catch(IOException e) {
            System.err.println("Did not properly send Memo to: " + clientSocket.getInetAddress().getAddress());
            closeSocket();
        }
    }
    
    private void closeSocket() {
        try {
            clientSocket.close();
        } catch(IOException e) {
            System.err.println("Client: " + clientSocket.getInetAddress().getAddress() + " refuses to close!");
        }
    }
    
}