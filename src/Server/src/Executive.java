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
            Packet inPacket = parsePacket(in);
            if (inPacket != null) {
                invokeMarketer(inPacket);
            } else {
                closeSocket();
            }
        } catch(IOException e) {
            sendResponse(new Packet(500, "HTML", "<html>Whoops, something went wrong</html>"));
            System.err.println("Did not properly read packet from: " + clientSocket.getInetAddress().getAddress());
        }
    }

    public Packet parsePacket(BufferedReader in) {
        Analyst parser = new Analyst(in);
        parser.parse();
        return parser.getPacket();
    }
    
    public void invokeMarketer(Packet inPacket) {
        marketer.execute(inPacket, routes, this);
    }

    public void sendResponse(Packet packet) {
        try {
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            PacketPresenter presenter = new PacketPresenter(packet);
            out.write(presenter.getOutPacket().getBytes());
            closeSocket();
        } catch(IOException e) {
            System.err.println("Did not properly send packet to: " + clientSocket.getInetAddress().getAddress());
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
