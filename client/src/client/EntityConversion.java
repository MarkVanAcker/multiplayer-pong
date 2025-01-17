package client;

import com.badlogic.gdx.graphics.Texture;
import entityG.EntityG;
import entityG.PlayerG;
import factories.BallFactory;
import factories.Factory;
import factories.PlayerFactory;
import packets.InitEntityPacket;
import packets.InitPlayerPacket;
import util.EntityTypesInit;
import util.json.Json;
import util.json.JsonObject;
import util.json.JsonValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public final class EntityConversion {

    //All factories hardcoded

    private static HashMap<String, Factory> factories = new HashMap<>();

    private EntityConversion() { }

    public static EntityG convertInitEntityToEntity(InitEntityPacket packet) {
        return factories.get(packet.type).
                getInstance(packet.position, packet.dimension, packet.id);
    }

    //TODO: return type should probably be EntityG
    public static PlayerG convertInitPlayerToPlayer(InitPlayerPacket packet) {
        return (PlayerG) factories.get(packet.type).
                getInstance(packet.position, packet.dimension, packet.id);
    }

    public static void init() {
        JsonObject types = EntityTypesInit.getEntityTypes();
        //this is ugly hihi
        factories.put(types.get("ball").asArray().get(0).asObject().get("name").asString(),
                new BallFactory(new Texture(types.get("ball").asArray().get(0).asObject().get("filename").asString())));

        factories.put(types.get("player").asArray().get(0).asObject().get("name").asString(),
                new PlayerFactory(new Texture(types.get("player").asArray().get(0).asObject().get("filename").asString())));
    }
}
