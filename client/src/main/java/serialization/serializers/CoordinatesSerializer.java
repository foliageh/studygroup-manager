package serialization.serializers;

import models.Coordinates;
import serialization.exceptions.NotValidatedYetException;
import serialization.fields.DoubleField;
import serialization.fields.IntegerField;

public class CoordinatesSerializer extends Serializer<Coordinates> {
    {
        fields.put("x", new IntegerField("X coordinate", false, -261));
        fields.put("y", new DoubleField("Y coordinate", false, -162D));
    }

    @Override
    public Coordinates toValue() {
        if (!isValid())
            throw new NotValidatedYetException("You should run validate() method first.");
        return new Coordinates((int) fields.get("x").toValue(),
                               (double) fields.get("y").toValue());
    }
}
