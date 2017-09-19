package client;

import client.ClientGame;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import packets.EntityChangePositionPacket;
import packets.InitEntityPacket;

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
