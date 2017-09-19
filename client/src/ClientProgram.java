import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import packets.EntityChangePositionPacket;
import packets.InitEntityPacket;
import util.Register;

import java.io.IOException;
import java.util.Queue;

public class ClientProgram extends Listener {


    ClientGame clientgame;


    ClientProgram(ClientGame cg){
        clientgame = cg;
    }


    @Override
    public void received(Connection connection, Object o) {
        if(o instanceof EntityChangePositionPacket){
            EntityChangePositionPacket e = (EntityChangePositionPacket)o;
            clientgame.addToEntityQueue(e);
        }else if(o instanceof InitEntityPacket){
            InitEntityPacket e = (InitEntityPacket)o;

        }
    }

}
