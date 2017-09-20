package entity;

import com.badlogic.gdx.math.Vector2;

public class Ball extends Entity {

    Vector2 velocity;

    public Ball(Vector2 position, Vector2 dimension, Vector2 velocity, String typeId) {
        super(position, dimension, typeId);
        this.velocity = velocity;
    }

    @Override
    public void update(float deltaT) {
        position.mulAdd(velocity, deltaT);
        if (position.x > 500) {
            position.x = 499;
            velocity.scl(-1, 0);
        }
        if (position.x < 100) {
            position.x = 101;
            velocity.scl(-1, 0);
        }
        changed = true;
    }
}
