package server;

import entity.Entity;
import packets.EntityChangePositionPacket;

import java.util.HashMap;

public class World {

    private final HashMap<Long, Entity> entities = new HashMap<Long, Entity>();

    private Game game;

    public World(Game game) {
        this.game = game;
    }

    public void addEntity(Entity e) {
        entities.put(e.getId(), e);
    }

    public void update(float deltaT) {

        for (Entity e : entities.values()) {
            e.update(deltaT);
        }

        for (Entity e : entities.values()) {
            if (e.isChanged()) {
                EntityChangePositionPacket packet = EntityConversion.convertEntityToChangePositionPacket(e);
                packet.time = System.currentTimeMillis();
                game.sendPacketToAllPlayersUDP(packet);
                e.setChanged(false);
            }
        }

        remove();
    }

    public void remove() {
        for (Entity e : entities.values()) {
            if (e.isRemoved())
                entities.remove(e.getId());
        }
    }

    public Entity getEntity(long id) {
        return entities.get(id);
    }
}
