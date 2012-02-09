package Server.src;

import Server.src.persistance.src.InMemoryDB;
import Server.src.persistance.src.Persistable;
import TTTGame.src.AI;
import TTTGame.src.OnlineGameManager;
import TTTGame.src.OnlineHuman;
import TTTGame.src.Player;

import java.util.HashMap;
import java.util.UUID;

public class TicTacToeClient implements Client {

    private HashMap<String, OnlineGameManager> games;
    private Persistable database;

    private Packet packet;
    
    public TicTacToeClient() {
        games = new HashMap<String, OnlineGameManager>();
        database = InMemoryDB.getInstance();
    }

    public void setPacket(Packet _packet) {
        packet = _packet;
    }

    public Packet getPacket() {
        return packet;
    }

    public void execute(Marketer marketer) {
        try {
            readState();
            String path = retrievePath();
            if(path.equals("/ttt")) {
                newGamePacket(marketer);
            } else if(path.equals("/humanvshuman")) {
                generateNewGame(new OnlineHuman(1), new OnlineHuman(-1), marketer);
            } else if(path.equals("/humanvsai")) {
                generateNewGame(new OnlineHuman(1), new AI(-1), marketer);
            } else if(path.equals("/aivshuman")) {
                generateNewGame(new AI(1), new OnlineHuman(-1), marketer);
            } else if(path.startsWith("/move")) {
                OnlineGameManager game = games.get(retrieveCookie());
                game.setHumanMove(Integer.parseInt(path.charAt(5) + ""), Integer.parseInt(path.charAt(6) + ""));
                game.nextTurn();
                games.put(retrieveCookie(), game);
                setPacket(new Packet(200, "HTML", game.drawBoard()));
            } else {
                setPacket(new Packet(404, "HTML", "<html>Not Found</html>"));
            }
            saveState();
            contactMarketer(marketer, getPacket());
        } catch(NullPointerException e) {
            setPacket(new Packet(500, "HTML", "<html>Internal Server Error</html>"));
            contactMarketer(marketer, getPacket());
        }
    }
    
    public String retrievePath() {
        try {
            return packet.getField("Path");
        } catch(Exception e) {
            System.err.println("No path");
            setPacket(new Packet(400, "TEXT"));
            return null;
        }
    }

    public String retrieveCookie() {
        try {
            return packet.getField("Cookie");
        } catch(Exception e) {
            return null;
        }
    }

    private void generateNewGame(Player p1, Player p2, Marketer marketer) {
        OnlineGameManager game = new OnlineGameManager(p1, p2);
        String cookie = UUID.randomUUID().toString();
        games.put(cookie, game);
        setPacket(new Packet(200, "HTML", game.drawBoard()));
        packet.setField("Set-Cookie", cookie);
    }

    public void contactMarketer(Marketer marketer, Packet packet) {
        marketer.clientResponse(packet);
    }

    private void newGamePacket(Marketer marketer) {
        Packet packet = new Packet(200, "HTML", "<html>\n" +
                "Game Types:\n" +
                "<a href=\"/humanvshuman\">Human vs. Human</a>\n" +
                "<a href=\"/humanvsai\">Human vs. AI</a>\n" +
                "<a href=\"/aivshuman\">AI vs. Human</a>\n" +
                "</html>");
        packet.setField("Set-Cookie", "");
        setPacket(packet);
    }


    private void readState() {
        HashMap entry = (HashMap<String, OnlineGameManager>)database.read("TicTacToe");
        if(entry != null) {
            games = entry;
        }
    }

    private void saveState() {
        database.write("TicTacToe", games);
    }

}