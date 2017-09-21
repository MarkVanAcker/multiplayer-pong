package entity;

import com.badlogic.gdx.math.Vector2;
import server.World;

public abstract class Entity {

    public static long nextId = 0;

    protected Vector2 position, dimension;
    protected final long id;
    protected boolean removed, changed;
    protected String typeId;

    public static final String TypePlayer = "player";

    public static final String TypeBall = "ball";

    public Entity() {
        this.id = getNextId();
    }

    public Entity(Vector2 position, Vector2 dimension, String typeId) {
        this();
        this.position = position;
        this.dimension = dimension;
        this.typeId = typeId;
    }

    public abstract void update(float deltaT);

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDimension() {
        return dimension;
    }

    public void addPosition(Vector2 v) {
        position.add(v);
        changed = true;
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

    public String getTypeId() {
        return typeId;
    }

    public abstract String getType();

    public abstract boolean isMovable();

    public abstract void handleCollision(Entity e, World.CollisionDirection dir);
}
