package entity;

import com.badlogic.gdx.math.Vector2;
import server.World;

public class ScoreBoard extends Entity {

    private ScorePlatform left, right;

    private int leftScore, rightScore;

    public ScoreBoard(Vector2 position, Vector2 dimension, String typeId, ScorePlatform left, ScorePlatform right) {
        super(position, dimension, typeId);
        this.left = left;
        this.right = right;
    }

    @Override
    public void update(float deltaT) {
        if (leftScore != left.getBallCollisions() || rightScore != right.getBallCollisions()) {
            leftScore = left.getBallCollisions();
            rightScore = right.getBallCollisions();
            System.out.println("Score: " + leftScore + " - " + rightScore);

            //TODO: we want to send this information to the player eventually, but there are no packets for this right now
            // maybe introduce a specific method for every entity to generate a packet when changed is true
            //changed = true;
        }
    }

    @Override
    public String getType() {
        return Entity.TYPE_SCOREBOARD;
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public void handleCollision(Entity e, World.CollisionDirection dir) {

    }
}
