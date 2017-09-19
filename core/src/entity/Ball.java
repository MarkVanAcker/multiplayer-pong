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
        position.add(velocity.scl(deltaT));
        changed = true;
    }
}
