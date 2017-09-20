package client;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.esotericsoftware.kryonet.Client;
import entityG.EntityG;
import packets.EntityChangePositionPacket;
import packets.Packet;
import util.Register;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ClientGame implements Runnable {

    Client client;
    ClientProgram clientprogram;
    private Queue<EntityChangePositionPacket> changePositionQueue = new LinkedList<>();

    private final HashMap<Long, EntityG> entities = new HashMap<>();

    EntityG player;

    public ClientGame(final String hostname ) throws IOException {
        EntityConversion.init();

        client = new Client();
        clientprogram = new ClientProgram(this);
        client.addListener(clientprogram);
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

        //TODO: should this be a concurretnLinkedQueue? See also in core.src.server.Game
        while (!changePositionQueue.isEmpty()) {
            EntityChangePositionPacket packet = changePositionQueue.poll();
            entities.get(packet.id).updatePosition(packet.position, packet.time);
        }
    }

    public void addEntity(EntityG e) {
        entities.put(e.getId(), e);
    }

    public HashMap<Long, EntityG> getEntities() {
        return entities;
    }
}
