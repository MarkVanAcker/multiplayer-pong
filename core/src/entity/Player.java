package entity;

import com.badlogic.gdx.math.Vector2;
import server.World;

public class Player extends Entity {

    private float speed = 400f;

    public Player(Vector2 position, Vector2 dimension, String typeId) {
        super(position, dimension, typeId);
    }

    @Override
    public void update(float deltaT) {

    }

    public void addPosition(float deltaT, Vector2 dir) {
        Vector2 normDir = dir.cpy().nor();
        position.mulAdd(normDir, speed * deltaT);
        changed = true;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getType(){
        return TYPE_PLAYER;
    }

    @Override
    public boolean isMovable(){
        return false;
    }

    @Override
    public void handleCollision(Entity e, World.CollisionDirection dir){

    }



}
