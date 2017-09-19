package entity;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    public static long nextId = 0;

    protected Vector2 position, dimension;
    protected final long id;
    protected boolean removed, changed;

    public Entity() {
        this.id = getNextId();
    }

    public Entity(Vector2 position, Vector2 dimension) {
        this();
        this.position = position;
        this.dimension = dimension;
    }

    public abstract void update(float deltaT);

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDimension() {
        return dimension;
    }

    public long getId() {
        return id;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public boolean isChanged() {
        return changed;
    }

    public long getNextId() {
        return nextId++;
    }

    public abstract String getTypeId();
}
