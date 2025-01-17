package factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import entityG.EntityG;
import entityG.PlayerG;

public class PlayerFactory extends Factory {

    public PlayerFactory(Texture texture) {
        super(texture);
    }

    @Override
    public EntityG getInstance(Vector2 position, Vector2 dimension, long id) {
        return new PlayerG(position, dimension, id, texture);
    }
}
