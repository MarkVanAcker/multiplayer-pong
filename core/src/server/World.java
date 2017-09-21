package server;

import entity.Entity;
import packets.EntityChangePositionPacket;
import packets.EntityRemovePacket;
import packets.PlayerKeyboardPacket;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class World {

    private final ConcurrentHashMap<Long, Entity> entities = new ConcurrentHashMap<Long, Entity>();

    private Game game;

    public enum CollisionDirection {
        UP, DOWN, LEFT, RIGHT, NULL
    }

    public World(Game game) {
        this.game = game;
    }

    public void addEntity(Entity e) {
        entities.put(e.getId(), e);
    }

    public void update(float deltaT) {

        synchronized (entities) {
            for (Entity e : entities.values()) {
                e.update(deltaT);
            }


            for (Entity e : entities.values()) {
                if (e.isMovable()) {
                    for (Entity i : entities.values()) {
                        if (i.getId() == e.getId()) {
                            continue;
                        } else {
                            if (checkCollision(e, i)) {
                                applyCollision(e, i);
                            }
                        }

                    }
                }
            }

            for (Entity e : entities.values()) {
                if (e.isChanged()) {
                    EntityChangePositionPacket packet = EntityConversion.convertEntityToChangePositionPacket(e);
                    packet.time = System.currentTimeMillis();
                    game.sendPacketToAllPlayersUDP(packet);
                    e.setChanged(false);
                }
            }
        }

        remove();
    }

    public void reset() {
        synchronized (entities) {
            for (Entity e : entities.values()) {
                e.remove();
            }
        }
        remove();
    }

    public void remove() {
        synchronized (entities) {
            for (Entity e : entities.values()) {
                if (e.isRemoved()) {
                    EntityRemovePacket packet = new EntityRemovePacket();
                    packet.id = e.getId();
                    game.sendPacketToAllPlayersTCP(packet);
                    entities.remove(e.getId());
                }
            }
        }
    }

    public Entity getEntity(long id) {
        return entities.get(id);
    }

    private boolean checkCollision(Entity e1, Entity e2){
        if(e1.getPosition().x < e2.getPosition().x + e2.getDimension().x && e2.getPosition().x < e1.getPosition().x + e1.getDimension().x && e1.getPosition().y < e2.getPosition().y + e2.getDimension().y && e2.getPosition().y < e1.getPosition().y + e1.getDimension().y){
            return true;
        }else{
            return false;
        }
    }


    private void applyCollision(Entity movableobj, Entity collobj){

        float dx = Math.min(movableobj.getPosition().x+movableobj.getDimension().x-collobj.getPosition().x, collobj.getPosition().x+collobj.getDimension().x-movableobj.getPosition().x);
        float dy = Math.min(movableobj.getPosition().y+movableobj.getDimension().y-collobj.getPosition().y, collobj.getPosition().y+collobj.getDimension().y-movableobj.getPosition().y);


        float moveAmountX = (collobj.isMovable()) ? dx/2 : dx;
        float moveAmountY = (collobj.isMovable()) ? dy/2 : dy;

        CollisionDirection movdir = CollisionDirection.NULL,colldir = CollisionDirection.NULL;

        if(dx <= dy){
            if(movableobj.getPosition().x < collobj.getPosition().x){
                movableobj.getPosition().add(-moveAmountX,0);
                collobj.getPosition().add(dx - moveAmountX,0);
                movdir = CollisionDirection.LEFT;
                colldir = CollisionDirection.RIGHT;
            }else{
                movableobj.getPosition().add(moveAmountX,0);
                collobj.getPosition().add(-dx+moveAmountX,0);
                movdir = CollisionDirection.RIGHT;
                colldir = CollisionDirection.LEFT;
            }
        }
        if(dy <= dx){
            if(movableobj.getPosition().y < collobj.getPosition().y){
                movableobj.getPosition().add(0,-moveAmountY);
                collobj.getPosition().add(0,dy-moveAmountY);
                movdir = CollisionDirection.DOWN;
                colldir = CollisionDirection.UP;
            }else{
                movableobj.getPosition().add(0,moveAmountY);
                collobj.getPosition().add(0,-dy+moveAmountY);
                movdir = CollisionDirection.UP;
                colldir = CollisionDirection.DOWN;
            }
        }


        //TODO: Diagonaal ENUM DIRECTION

        movableobj.handleCollision(collobj,colldir);
        collobj.handleCollision(movableobj,movdir);
    }
}
