package factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import entityG.EntityG;

public abstract class Factory {

    public abstract EntityG getInstance(Vector2 position, Vector2 dimension, long id, Texture texture);
}