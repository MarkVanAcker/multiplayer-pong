package entity;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    protected Vector2 position, dimension;
    protected int id;
    protected boolean removed, changed;

    public abstract void update();

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDimension() {
        return dimension;
    }

    public int getId() {
        return id;
    }

    public boolean isRemoved() {
        return removed;
    }

    public boolean isChanged() {
        return changed;
    }
}
