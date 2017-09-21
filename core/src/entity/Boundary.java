package entity;

import com.badlogic.gdx.math.Vector2;
import server.World;

public class Boundary extends Entity {

    public Boundary(Vector2 position, Vector2 dimension, String s) {
        super(position, dimension, s);
    }

    @Override
    public void update(float deltaT) {

    }

    @Override
    public String getType() {
        return Entity.TYPE_BOUNDARY;
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public void handleCollision(Entity e, World.CollisionDirection dir) { }
}
