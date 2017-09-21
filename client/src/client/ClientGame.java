package client;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.esotericsoftware.kryonet.Client;
import entityG.EntityG;
import entityG.PlayerG;
import packets.EntityChangePositionPacket;
import packets.Packet;
import packets.PlayerKeyboardPacket;
import util.Register;

import java.io.IOException;
import java.util.*;

public class ClientGame implements Runnable {

    Client client;
    ClientProgram clientProgram;
    Input clientInput;

    private PlayerKeyboardPacket inputPacket;

    private Queue<EntityChangePositionPacket> changePositionQueue = new LinkedList<>();

    private final HashMap<Long, EntityG> entities = new HashMap<>();

    PlayerG player;

    public ClientGame(final String hostname, Input clientInput) throws IOException {
        EntityConversion.init();

        this.clientInput = clientInput;
        inputPacket = new PlayerKeyboardPacket();

        client = new Client();
        clientProgram = new ClientProgram(this);
        client.addListener(clientProgram);
        client.start();
        Texture t = new Texture("client/assets/pic2.png");
        /*EntityG e = new EntityG(new Vector2(100,100),new Vector2(100,100),1, t);
        entities.put(e.getId(), e);*/
        Register.register(client);
        client.connect(5000,hostname,Register.port,Register.port);

        new Thread(this).start();
    }
    @Override
    public void run() {

        long sleeptime = 1000/120;
        long prevTime = System.nanoTime();

        while (true) {
            update(((float)(System.nanoTime() - prevTime))/1000000000);
            prevTime = System.nanoTime();

            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void sendPacketTCP(Packet packet) {
        client.sendTCP(packet);
    }

    public void sendPacketUDP(Packet packet) {
        client.sendUDP(packet);
    }

    public void addToEntityQueue(EntityChangePositionPacket e){
        changePositionQueue.add(e);
    }

    public void update(float deltaT){
        //predict
        //analyze queue
        //update
        //send keyboard info

        if (clientInput.isKeyPressed(Input.Keys.Z)) {
            inputPacket.up = true;
        }
        if (clientInput.isKeyPressed(Input.Keys.S)) {
            inputPacket.down = true;
        }
        sendPacketUDP(inputPacket);
        inputPacket.up = false;
        inputPacket.down = false;

        //TODO: should this be a concurretnLinkedQueue? See also in core.src.server.Game
        while (!changePositionQueue.isEmpty()) {
            EntityChangePositionPacket packet = changePositionQueue.poll();
            entities.get(packet.id).updatePosition(packet.position, packet.time);
        }
    }

    public void addEntity(EntityG e) {
        entities.put(e.getId(), e);
    }

    public void addPlayer(PlayerG e) {
        player = e;
        addEntity(e);
        inputPacket.id = player.getId();
    }

    public HashMap<Long, EntityG> getEntities() {
        return entities;
    }
}
