package util;

import entity.Entity;
import packets.InitEntityPacket;

//singleton class
public final class EntityConversion {

    private EntityConversion(){
    }

    public static InitEntityPacket ConvertEntityToPacket(Entity e) {
        InitEntityPacket packet = new InitEntityPacket();
        packet.position = e.getPosition();
        packet.dimension = e.getDimension();
        packet.id = e.getId();
        return packet;
    }
}
