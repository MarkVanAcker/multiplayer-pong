package server;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import entity.Ball;
import entity.Player;
import packets.InitEndPacket;
import packets.InitEntityPacket;
import packets.PlayerKeyboardPacket;
import util.EntityConversion;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Game implements Runnable {

    private boolean running = false;

    private World world;
    private ServerProgram serverProgram;
    private HashMap<Long, Connection> idToPlayerConnection = new HashMap<Long, Connection>();

    //ConcurrentLinkedQueue is a thread safe implementation of  a queue
    //important: if we want to loop over this thing we need to synchronize that loop, because
    //it is not an atomic operation
    private final ConcurrentLinkedQueue<PlayerKeyboardPacket> playerKeyboards = new ConcurrentLinkedQueue<PlayerKeyboardPacket>();

    public Game(ServerProgram serverProgram) {
        this.serverProgram = serverProgram;
        world = new World();
    }

    //creates the world and starts the game
    public void startGame(Connection player1Connection, Connection player2Connection) {
        //create all entities: should be done in a separate class later
        Ball ball = new Ball(new Vector2(0.0f, 0.0f), new Vector2(30, 30), new Vector2(10.0f, 0.0f));
        world.addEntity(ball);
        Player player1 = new Player(new Vector2(-10.0f, 0.0f), new Vector2(20, 100));
        world.addEntity(player1);
        idToPlayerConnection.put(player1.getId(), player1Connection);
        Player player2 = new Player(new Vector2(10.0f, 0.0f), new Vector2(20, 100));
        world.addEntity(player2);
        idToPlayerConnection.put(player2.getId(), player1Connection);

        //send packets to players: should also be done somewhere else
        InitEntityPacket player1Packet = EntityConversion.ConvertEntityToPacket(player1);
        InitEntityPacket player2Packet = EntityConversion.ConvertEntityToPacket(player2);
        InitEntityPacket ballPacket = EntityConversion.ConvertEntityToPacket(ball);

        player1Connection.sendTCP(player1Packet);
        player1Connection.sendTCP(player2Packet);
        player1Connection.sendTCP(ballPacket);
        player2Connection.sendTCP(player1Packet);
        player2Connection.sendTCP(player2Packet);
        player2Connection.sendTCP(ballPacket);

        InitEndPacket endPacket = new InitEndPacket();
        endPacket.succes = true;
        player1Connection.sendTCP(endPacket);
        player2Connection.sendTCP(endPacket);

        //Start the game
        new Thread(this).start();
        running = true;
    }

    public void addPlayerKeyboardPacket(PlayerKeyboardPacket packet) {
        if (running)
            playerKeyboards.add(packet);
    }

    private void update(float deltaT) {
        synchronized (playerKeyboards) {
            if (!playerKeyboards.isEmpty()) {
                PlayerKeyboardPacket packet = playerKeyboards.poll();
                //TODO: do stuff with this packet
            }
        }

        world.update(deltaT);
    }

    @Override
    public void run() {

        //1s = 1 000 000 000 nanos

        long sleeptime = 1000/60;
        long prevTime = System.nanoTime();

        while (running) {
            update(((float)(System.nanoTime() - prevTime))/1000000000);
            prevTime = System.nanoTime();

            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
