package serialize;

import model.Child;
import model.TopLevel;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

public class ChildSerializer implements JsonbSerializer<Child> {
    @Override
    public void serialize(Child obj, JsonGenerator generator, SerializationContext ctx) {
        if (obj != null) {
            generator.writeStartObject();
            generator.write("id", obj.getId().toString());
            generator.write("name", obj.getName());
            ctx.serialize("status", obj.getStatus(), generator);
            serializeTop(obj.getTop(), generator, ctx);
            serializeParent(obj.getParent(), generator, ctx);

            generator.writeStartArray("subElement");
            for (Child curChild : obj.getSubElements()) {
                serialize(curChild, generator, ctx);
            }
            generator.writeEnd();

            generator.writeEnd();
        } else {
            ctx.serialize(null, generator);
        }
    }

    private void serializeTop(TopLevel obj, JsonGenerator generator, SerializationContext ctx) {
        if (obj != null) {
            generator.writeStartObject("top");
            generator.write("id", obj.getId().toString());
            generator.write("name", obj.getName());
            ctx.serialize("status", obj.getStatus(), generator);
            generator.writeEnd();
        }
    }

    private void serializeParent(Child obj, JsonGenerator generator, SerializationContext ctx) {
        if (obj != null) {
            generator.writeStartObject("parent");
            generator.write("id", obj.getId().toString());
            generator.write("name", obj.getName());
            ctx.serialize("status", obj.getStatus(), generator);
            generator.writeEnd();
        }
    }
}
