package adapter;

import javax.json.Json;
import javax.json.JsonString;
import javax.json.bind.adapter.JsonbAdapter;
import java.util.UUID;

public class UUIDAdapter implements JsonbAdapter<UUID, JsonString> {
    @Override
    public JsonString adaptToJson(UUID obj) throws Exception {
        JsonString result = Json.createValue(obj.toString());

        return result;
    }

    @Override
    public UUID adaptFromJson(JsonString obj) throws Exception {
        UUID result = UUID.fromString(obj.getString());
        return result;
    }
}
