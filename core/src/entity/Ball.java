package entity;

import com.badlogic.gdx.math.Vector2;
import server.World;

public class Ball extends Entity {

    Vector2 velocity;

    float speed;

    public Ball(Vector2 position, Vector2 dimension, Vector2 velocity, String typeId) {
        super(position, dimension, typeId);
        this.velocity = velocity;
        speed = velocity.len();
    }

    @Override
    public void update(float deltaT) {
        if (position.x > 400) {
            position.x = 399;
            velocity.x = -velocity.x;
        }

        position.mulAdd(velocity, deltaT);
        changed = true;
    }

    public void collide(){

    }

    public String getType(){
        return TypeBall;
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

        /*if(e.getType().equals(Entity.TypePlayer) && (dir == World.CollisionDirection.LEFT || dir == World.CollisionDirection.RIGHT)){
            float dy = (2*(this.getPosition().y + this.getDimension().y/2 - e.getPosition().y + e.getDimension().y/2))/(e.getDimension().y);
            float angle = (float) (dy* Math.PI/3);
            velocity.setAngleRad(angle);
        }*/
    }
}
