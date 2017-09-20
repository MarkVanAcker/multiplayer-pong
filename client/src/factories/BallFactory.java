package factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import entityG.BallG;
import entityG.EntityG;

public class BallFactory extends Factory{

    public BallFactory(Texture texture) {
        super(texture);
    }

    @Override
    public BallG getInstance(Vector2 position, Vector2 dimension, long id) {
        return new BallG(position, dimension, id, texture);
    }
}
