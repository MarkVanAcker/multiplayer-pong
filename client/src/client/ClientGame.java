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
import java.util.Queue;

public class ClientGame implements Runnable{

    Client client;
    ClientProgram clientprogram;
    private Queue<EntityChangePositionPacket> queue;

    ArrayList<EntityG> entities = new ArrayList<EntityG>();

    EntityG player;

    public ClientGame(final String hostname ) throws IOException {
        client = new Client();
        clientprogram = new ClientProgram(this);
        client.addListener(clientprogram);
        client.start();
        EntityG e = new EntityG(new Vector2(100,100),new Vector2(100,100),1,new Texture("client/src/client/pic2.png"));
        entities.add(e);
        Register.register(client);
        client.connect(5000,hostname,Register.port,Register.port);

        new Thread(this).start();
    }
    @Override
    public void run() {
        this.update();


    }

    public void addToEntityQueue(EntityChangePositionPacket e){
        queue.add(e);
    }

    public void update(){

    }

    public ArrayList<EntityG> getEntities() {
        return entities;
    }
}
