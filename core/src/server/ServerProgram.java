package server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import packets.ClientConnectPacket;
import util.Register;

import java.io.IOException;
import java.util.ArrayList;

public class ServerProgram extends Listener {

    public static Server server;
    static ArrayList<Connection> players = new ArrayList<Connection>();

    public static void main(String[] args) throws IOException{
        server = new Server();
        Register.register(server);
        server.bind(Register.port);
        server.start();
        server.addListener(new ServerProgram());
        System.out.println("Server is running...");
    }

    @Override
    public void connected(Connection connection) {
        //TODO: if more players join, send something
        players.add(connection);

        if (players.size() == 2) {
            //start the game
        }
    }

    @Override
    public void received(Connection connection, Object object) {

    }

    @Override
    public void disconnected(Connection connection) {

    }
}
