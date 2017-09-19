package factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import entityG.EntityG;

public class PlayerFactory extends Factory {

    Texture texture;

    public PlayerFactory(Texture texture) {
        this.texture = texture;
    }

    @Override
    public EntityG getInstance(Vector2 position, Vector2 dimension, long id, Texture texture) {
        return null;
    }
}
