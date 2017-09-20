package client;

import client.ClientGame;
import com.badlogic.gdx.Game;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import packets.*;

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
        }else if(o instanceof InitEntityPacket) {
            InitEntityPacket e = (InitEntityPacket) o;
            clientgame.addEntity(EntityConversion.convertInitEntityToEntity(e));
        } else if (o instanceof InitPlayerPacket) {
            InitPlayerPacket p = (InitPlayerPacket) o;
            clientgame.addEntity(EntityConversion.convertInitPlayerToPlayer(p));
        } else if(o instanceof InitEndPacket) {
            GameStartPacket packet = new GameStartPacket();
            packet.success = true;
            clientgame.sendPacketTCP(packet);
        }
    }

}
