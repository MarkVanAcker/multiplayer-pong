package entityG;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class EntityG extends Actor {

    private Texture text;
    private long id;

    public EntityG(Vector2 pos, Vector2 dim, long id, Texture text){

        this.setX(pos.x);
        this.setY(pos.y);
        this.setWidth(dim.x);
        this.setHeight(dim.y);
        this.text = text;
        this.id = id;

    }

    @Override
    public void draw(Batch batch,float parentAlpha){
        batch.draw(this.text,this.getX(),this.getY());
    }

}
