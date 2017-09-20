package server;

import entity.Entity;
import packets.EntityChangePositionPacket;
import packets.InitEntityPacket;
import packets.InitPlayerPacket;

//singleton class
public final class EntityConversion {

    private EntityConversion(){
    }

    public static InitEntityPacket convertEntityToInitPacket(Entity e) {
        InitEntityPacket packet = new InitEntityPacket();
        packet.position = e.getPosition();
        packet.dimension = e.getDimension();
        packet.id = e.getId();
        packet.type = e.getTypeId();

        return packet;
    }

    public static InitPlayerPacket convertPlayerToInitPacket(Entity e) {
        InitPlayerPacket packet = new InitPlayerPacket();
        packet.position = e.getPosition();
        packet.dimension = e.getDimension();
        packet.id = e.getId();
        packet.type = e.getTypeId();

        return packet;
    }

    public static EntityChangePositionPacket convertEntityToChangePositionPacket(Entity e) {
        EntityChangePositionPacket packet = new EntityChangePositionPacket();
        packet.id = e.getId();
        packet.position = e.getPosition();

        return packet;
    }
}
