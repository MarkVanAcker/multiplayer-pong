package entity;

import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {

    public Player(Vector2 position, Vector2 dimension, String typeId) {
        super(position, dimension, typeId);
    }

    @Override
    public void update(float deltaT) {
        changed = false;
    }
}
