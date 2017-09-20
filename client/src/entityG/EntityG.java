package entityG;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class EntityG extends Rectangle {

    private Texture text;
    private long id;
    private long lastUpdate;

    public EntityG(Vector2 pos, Vector2 dim, long id, Texture text){

        this.setX(pos.x);
        this.setY(pos.y);
        this.setWidth(dim.x);
        this.setHeight(dim.y);
        this.text = text;
        this.id = id;

        lastUpdate = Long.MIN_VALUE;
    }

    public void updatePosition(Vector2 position, long time) {
        if (lastUpdate < time) {
            this.setPosition(position);
            lastUpdate = time;
        }
    }

    public Texture getTexture() {
        return text;
    }

    public long getId() {
        return id;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
