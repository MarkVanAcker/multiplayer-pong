package util;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import packets.*;

public final class Register {

    public static final int tcpPort = 25123;
    public static final int udpPort = 25483;

    private Register() {}

    public static void register(EndPoint endpoint) {
        Kryo kryo = endpoint.getKryo();
        kryo.register(ClientConnectPacket.class);
        kryo.register(ClientDisconnectPacket.class);
        kryo.register(EntityChangePositionPacket.class);
        kryo.register(EntityRemovePacket.class);
        kryo.register(GameStartPacket.class);
        kryo.register(InitEndPacket.class);
        kryo.register(InitEntityPacket.class);
        kryo.register(PlayerKeyboardPacket.class);
        kryo.register(InitPlayerPacket.class);
        kryo.register(Vector2.class);
    }
}
