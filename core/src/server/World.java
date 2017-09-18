package server;

import entity.Entity;

import java.util.HashMap;

public class World {

    private HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();

    public World() {

    }

    public void addEntity(Entity e) {
        entities.put(e.getId(), e);
    }

    public void update(float deltaT) {
        for (Entity e : entities.values()) {
            e.update(deltaT);
        }
        remove();
    }

    public void remove() {
        for (Entity e : entities.values()) {
            entities.remove(e);
        }
    }
}
