package server;

import entity.Entity;
import packets.EntityChangePositionPacket;

import java.util.HashMap;

public class World {

    private final HashMap<Long, Entity> entities = new HashMap<Long, Entity>();

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

        for (Entity e : entities.values()) {
            e.update(deltaT);
        }



        for(Entity e: entities.values()){
            if(e.isMovable()){
                for(Entity i: entities.values()){
                    if(i.getId() == e.getId()){
                        continue;
                    }else{
                        if(checkCollision(e,i)){
                            applyCollision(e,i);
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


        float moveAmount = (collobj.isMovable()) ? dx/2 : dx;

        CollisionDirection movdir = CollisionDirection.NULL,colldir = CollisionDirection.NULL;

        if(dx <= dy){
            if(movableobj.getPosition().x < collobj.getPosition().x){
                movableobj.getPosition().add(-moveAmount,0);
                collobj.getPosition().add(dx - moveAmount,0);
                movdir = CollisionDirection.LEFT;
                colldir = CollisionDirection.RIGHT;
            }else{
                movableobj.getPosition().add(moveAmount,0);
                collobj.getPosition().add(-dx+moveAmount,0);
                movdir = CollisionDirection.RIGHT;
                colldir = CollisionDirection.LEFT;
            }
        }
        if(dy <= dx){
            if(movableobj.getPosition().y < collobj.getPosition().y){
                movableobj.getPosition().add(0,-moveAmount);
                collobj.getPosition().add(0,dy-moveAmount);
                movdir = CollisionDirection.DOWN;
                colldir = CollisionDirection.UP;
            }else{
                movableobj.getPosition().add(0,moveAmount);
                collobj.getPosition().add(0,-dy+moveAmount);
                movdir = CollisionDirection.UP;
                colldir = CollisionDirection.DOWN;
            }
        }


        //TODO: Diagonaal ENUM DIRECTION

        movableobj.handleCollision(collobj,movdir);
        collobj.handleCollision(movableobj,colldir);
    }
}
