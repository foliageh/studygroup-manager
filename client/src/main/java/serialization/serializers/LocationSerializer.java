package serialization.serializers;

import models.Location;
import serialization.exceptions.NotValidatedYetException;
import serialization.fields.DoubleField;
import serialization.fields.LongField;
import serialization.fields.StringField;

public class LocationSerializer extends Serializer<Location> {
    {
        fields.put("x", new DoubleField("X coordinate", false));
        fields.put("y", new DoubleField("Y coordinate", false));
        fields.put("z", new LongField("Z coordinate", false));
        fields.put("name", new StringField("Location name", true));
    }

    @Override
    public Location toValue() {
        if (!isValid())
            throw new NotValidatedYetException("You should run validate() method first.");
        return new Location((double) fields.get("x").toValue(),
                            (double) fields.get("y").toValue(),
                            (Long) fields.get("z").toValue(),
                            (String) fields.get("name").toValue());
    }
}
