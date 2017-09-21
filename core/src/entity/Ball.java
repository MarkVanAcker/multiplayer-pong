package entity;

import com.badlogic.gdx.math.Vector2;
import server.World;

public class Ball extends Entity {

    private static final float maxAngle = (float) Math.toRadians(50);

    Vector2 velocity;

    public Ball(Vector2 position, Vector2 dimension, Vector2 velocity, String typeId) {
        super(position, dimension, typeId);
        this.velocity = velocity;
    }

    @Override
    public void update(float deltaT) {
        position.mulAdd(velocity, deltaT);
        changed = true;
    }

    public void collide(){

    }

    public String getType(){
        return TYPE_BALL;
    }

    @Override
    public boolean isMovable(){
        return true;
    }

    @Override
    public void handleCollision(Entity e, World.CollisionDirection dir){
        if(dir == World.CollisionDirection.LEFT || dir == World.CollisionDirection.RIGHT){
            this.velocity.x = -this.velocity.x;
        }else if(dir == World.CollisionDirection.UP || dir == World.CollisionDirection.DOWN){
            this.velocity.y = -this.velocity.y;
        }else{
            System.out.println("got DIR NULL in Ball " + this.getId()  );
        }

        if(e.getType().equals(Entity.TYPE_PLAYER)){
            if (dir == World.CollisionDirection.LEFT) {
                float dy = (2 * (this.getPosition().y + this.getDimension().y / 2 - e.getPosition().y - e.getDimension().y / 2)) / (e.getDimension().y);
                float angle = (float) (dy * maxAngle);
                velocity.setAngleRad(angle);
            } else if (dir == World.CollisionDirection.RIGHT) {
                float dy = (2 * (this.getPosition().y + this.getDimension().y / 2 - e.getPosition().y - e.getDimension().y / 2)) / (e.getDimension().y);
                float angle = (float) (dy * maxAngle);
                velocity.setAngleRad((float) (Math.PI - angle));
            }
        }
    }
}
