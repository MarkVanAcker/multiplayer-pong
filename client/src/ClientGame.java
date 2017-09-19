import com.esotericsoftware.kryonet.Client;
import packets.EntityChangePositionPacket;
import util.Register;

import java.io.IOException;
import java.util.Queue;

public class ClientGame implements Runnable{

    Client client;
    ClientProgram clientprogram;
    private Queue<EntityChangePositionPacket> queue;


    ClientGame() throws IOException {
        client = new Client();
        clientprogram = new ClientProgram(this);
        client.addListener(clientprogram);
        client.start();

        Register.register(client);
        client.connect(5000,"localhost",Register.port,Register.port);

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
}
