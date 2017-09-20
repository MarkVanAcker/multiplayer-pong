package client;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.esotericsoftware.kryonet.Client;
import entityG.EntityG;
import packets.EntityChangePositionPacket;
import util.Register;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class ClientGame implements Runnable{

    Client client;
    ClientProgram clientprogram;
    private Queue<EntityChangePositionPacket> queue;

    private final HashMap<Long, EntityG> entities = new HashMap<>();

    EntityG player;

    public ClientGame(final String hostname ) throws IOException {
        client = new Client();
        clientprogram = new ClientProgram(this);
        client.addListener(clientprogram);
        client.start();
        EntityG e = new EntityG(new Vector2(100,100),new Vector2(100,100),1,new Texture("assets/pic2.png"));
        entities.put(e.getId(), e);
        Register.register(client);
        client.connect(5000,hostname,Register.port,Register.port);

        EntityConversion.init();

        new Thread(this).start();
    }
    @Override
    public void run() {

        long sleeptime = 1000/60;
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

    public void addToEntityQueue(EntityChangePositionPacket e){
        queue.add(e);
    }

    public void update(float deltaT){
        //analyze queue
        //update
        //send keyboard info
    }

    public void addEntity(EntityG e) {
        entities.put(e.getId(), e);
    }

    public HashMap<Long, EntityG> getEntities() {
        return entities;
    }
}
