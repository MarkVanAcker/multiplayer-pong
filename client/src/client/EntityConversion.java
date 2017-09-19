package client;

import entityG.EntityG;
import factories.Factory;
import packets.InitEntityPacket;
import util.json.Json;
import util.json.JsonObject;
import util.json.JsonValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public final class EntityConversion {

    //All factories hardcoded

    public static void main(String[] args) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String typesFileName = "common/src/util/EntityTypes.json";

    private static JsonObject entityTypes;
    private static HashMap<String, Factory> factories;

    private EntityConversion() { }

    public static EntityG convertInitEntityToEntity(InitEntityPacket packet) {

        return null;
    }

    public static void init() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(typesFileName));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        // delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();

        entityTypes = Json.parse(content).asObject();

        //System.out.println(entityTypes.get("ball").asArray().get(0).asObject().get("name").asString());
    }
}
