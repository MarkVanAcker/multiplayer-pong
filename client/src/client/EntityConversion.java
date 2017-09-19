package client;

import com.google.gson.Gson;
import entityG.EntityG;
import packets.InitEntityPacket;
import util.json.Json;
import util.json.JsonObject;
import util.json.JsonValue;

public final class EntityConversion {

    //All factories hardcoded

    public static void main(String[] args) {
        JsonObject object = Json.parse("{\"abc\":123}").asObject();
        System.out.println(object.get("abc").asInt());
    }

    private EntityConversion() { }

    public static EntityG convertInitEntityToEntity(InitEntityPacket packet) {

        return null;
    }
}
