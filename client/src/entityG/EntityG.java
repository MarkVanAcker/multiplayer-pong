package entityG;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class EntityG extends Actor {

    private Texture text;
    private Rectangle rec;
    private long id;

    public EntityG(Texture text,Rectangle rec, long id){

        this.text = text;
        this.rec = rec;
        this.id = id;

    }

    @Override
    public void draw(Batch batch,float parentAlpha){

    }

}
