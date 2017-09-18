package util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import packets.*;

public final class Register {

    public static final int port = 25123;

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
    }
}
