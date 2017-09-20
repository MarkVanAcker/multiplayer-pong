package util;

import util.json.Json;
import util.json.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EntityTypesInit {

    private static final String typesFileName = "common/src/util/EntityTypes.json";

    private static JsonObject entityTypes;

    public static void init() throws IOException {
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

    public static JsonObject getEntityTypes() {
        if (entityTypes == null) {
            try {
                init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return entityTypes;
    }
}
