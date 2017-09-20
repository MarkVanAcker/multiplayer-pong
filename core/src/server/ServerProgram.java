package server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import packets.ClientConnectPacket;
import packets.GameStartPacket;
import packets.PlayerKeyboardPacket;
import util.Register;

import java.io.IOException;
import java.util.ArrayList;

public class ServerProgram extends Listener {

    private static ServerProgram serverProgram;
    public static Server server;

    private Game game;
    private static ArrayList<Connection> players = new ArrayList<Connection>();

    public ServerProgram() {
        game = new Game(this);
    }

    public static void main(String[] args) throws IOException{
        server = new Server();
        Register.register(server);
        server.bind(Register.port, Register.port);
        server.start();
        serverProgram = new ServerProgram();
        server.addListener(serverProgram);
        System.out.println("Server is running...");
    }

    @Override
    public void connected(Connection connection) {
        //TODO: if more players join, send something
        players.add(connection);

        System.out.println("Someone connected at "+connection.getID());

        //TODO: should be a 2!
        if (players.size() == 1) {
            //start the game
            game.initGame(players.get(0), null);
        }
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof PlayerKeyboardPacket) {
            game.addPlayerKeyboardPacket((PlayerKeyboardPacket)object);
        } else if (object instanceof GameStartPacket) {
            game.incomingPlayerGameStartPacket();
        }
    }

    @Override
    public void disconnected(Connection connection) {
        //TODO: remove someone from the game if they disconnect
        System.out.println("Someone connected at "+connection.getID());
    }
}
