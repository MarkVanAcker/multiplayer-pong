package packets;

import com.badlogic.gdx.math.Vector2;

public class InitEntityPacket extends Packet {

    public long id;
    public Vector2 position, dimension;
    public String type;
}
