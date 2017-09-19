package client;

import com.esotericsoftware.kryonet.Client;
import packets.EntityChangePositionPacket;
import util.Register;

import java.io.IOException;
import java.util.Queue;

public class ClientGame implements Runnable{

    Client client;
    ClientProgram clientprogram;
    private Queue<EntityChangePositionPacket> queue;

    //Stage stage;

    public ClientGame(final String hostname ) throws IOException {
        client = new Client();
        clientprogram = new ClientProgram(this);
        client.addListener(clientprogram);
        client.start();

        Register.register(client);
        client.connect(5000,hostname,Register.port,Register.port);

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

    }

    /*
    public Stage getStage(){}
     */
}
