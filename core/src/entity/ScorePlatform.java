package entity;

import com.badlogic.gdx.math.Vector2;
import server.Game;
import server.World;

public class ScorePlatform extends Entity {

    private int ballCollisions = 0;

    public ScorePlatform(Vector2 position, Vector2 dimension, String typeId) {
        super(position, dimension, typeId);
    }

    @Override
    public void update(float deltaT) {

    }

    @Override
    public String getType() {
        return Entity.TYPE_SCOREPLATFORM;
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public void handleCollision(Entity e, World.CollisionDirection dir) {
        //A ball collided with the edge of the screen
        if (e.getType().equals(Entity.TYPE_BALL)) {
            ballCollisions++;
            Ball b = (Ball)e;
            b.position.set(Game.WIDTH / 2, Game.HEIGHT / 2);
            if (dir == World.CollisionDirection.LEFT)
                b.velocity.set(-300.0f, 0.0f);
            else if (dir == World.CollisionDirection.RIGHT)
                b.velocity.set(300.0f, 0.0f);
        }
    }

    public int getBallCollisions() {
        return ballCollisions;
    }
}
