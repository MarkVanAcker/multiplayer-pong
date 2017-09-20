package server;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import entity.Ball;
import entity.Player;
import packets.*;
import util.EntityTypesInit;
import util.json.JsonObject;

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
        world = new World(this);
    }

    //creates the world and starts the game
    public void initGame(Connection player1Connection, Connection player2Connection) {
        //Get the EntityTypes JsonObject to find names for the entities we are interested in
        JsonObject entityTypes = EntityTypesInit.getEntityTypes();

        //create all entities: should be done in a separate class later
        Ball ball = new Ball(new Vector2(0.0f, 0.0f), new Vector2(30, 30),
                new Vector2(10.0f, 0.0f),
                entityTypes.get("ball").asArray().get(0).asObject().get("name").asString());
        Player player1 = new Player(new Vector2(-100.0f, 0.0f),
                new Vector2(20, 100),
                entityTypes.get("player").asArray().get(0).asObject().get("name").asString());
        /*Player player2 = new Player(new Vector2(100.0f, 0.0f),
                new Vector2(20, 100),
                entityTypes.get("player").asArray().get(0).asObject().get("name").asString());*/

        //adds all entities to the world
        world.addEntity(ball);
        world.addEntity(player1);
        //world.addEntity(player2);

        //add connections, TODO: is this necessary?
        idToPlayerConnection.put(player1.getId(), player1Connection);
        //idToPlayerConnection.put(player2.getId(), player1Connection);

        //send packets to players: should also be done somewhere else
        InitPlayerPacket player1forSelfPacket = EntityConversion.convertPlayerToInitPacket(player1);
        InitEntityPacket player1forOtherPacket = EntityConversion.convertEntityToInitPacket(player1);
        //InitEntityPacket player2forOtherPacket = EntityConversion.convertEntityToInitPacket(player2);
        //InitPlayerPacket player2forSelfPacket = EntityConversion.convertPlayerToInitPacket(player2);
        InitEntityPacket ballPacket = EntityConversion.convertEntityToInitPacket(ball);

        //player1Connection.sendTCP(player1forSelfPacket);
        //player1Connection.sendTCP(player2forOtherPacket);
        player1Connection.sendTCP(ballPacket);
        /*player2Connection.sendTCP(player1forOtherPacket);
        player2Connection.sendTCP(player2forSelfPacket);
        player2Connection.sendTCP(ballPacket);*/

        InitEndPacket endPacket = new InitEndPacket();
        endPacket.succes = true;
        player1Connection.sendTCP(endPacket);
        //player2Connection.sendTCP(endPacket);
    }

    //starts the game
    private int playersGameStartPackets = 0;
    public void incomingPlayerGameStartPacket() {
        playersGameStartPackets++;
        if (playersGameStartPackets == 2) {
            new Thread(this).start();
            running = true;
        }
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

    public void sendPacketToAllPlayersUDP(Packet packet) {
        ServerProgram.server.sendToAllUDP(packet);
    }

    public void sendPacketToAllPlayersTCP(Packet packet) {
        ServerProgram.server.sendToAllTCP(packet);
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
