package client;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import packets.*;

public class ClientProgram extends Listener {


    private ClientGame clientGame;

    ClientProgram(ClientGame cg){
        clientGame = cg;
    }


    @Override
    public void received(Connection connection, Object o) {
        if(o instanceof EntityChangePositionPacket){
            EntityChangePositionPacket e = (EntityChangePositionPacket)o;
            clientGame.addToEntityQueue(e);
        }else if(o instanceof InitEntityPacket) {
            InitEntityPacket e = (InitEntityPacket) o;
            clientGame.addEntity(EntityConversion.convertInitEntityToEntity(e));
        } else if (o instanceof InitPlayerPacket) {
            InitPlayerPacket p = (InitPlayerPacket) o;
            clientGame.addPlayer(EntityConversion.convertInitPlayerToPlayer(p));
        } else if(o instanceof InitEndPacket) {
            GameStartPacket packet = new GameStartPacket();
            packet.success = true;
            clientGame.sendPacketTCP(packet);
        } else if (o instanceof EntityRemovePacket) {
            long id = ((EntityRemovePacket)o).id;
            clientGame.removeEntity(id);
        }
    }

}
