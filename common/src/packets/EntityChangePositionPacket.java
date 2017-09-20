package packets;

import com.badlogic.gdx.math.Vector2;

public class EntityChangePositionPacket extends Packet {

    public long id;
    public Vector2 position;
    public long time;
}
