package factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import entityG.EntityG;

public abstract class Factory {

    protected final Texture texture;

    public Factory(Texture texture) { this.texture = texture; }

    //TODO: save Texture or filepath to a texture and then have EntityG save the texture
    public abstract EntityG getInstance(Vector2 position, Vector2 dimension, long id);

    public Texture getTexture() {
        return texture;
    }
}
