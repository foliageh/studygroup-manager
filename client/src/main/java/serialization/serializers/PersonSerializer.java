package serialization.serializers;

import models.Color;
import models.Location;
import models.Person;
import serialization.exceptions.NotValidatedYetException;
import serialization.fields.DateField;
import serialization.fields.EnumField;
import serialization.fields.ModelField;
import serialization.fields.StringField;

public class PersonSerializer extends Serializer<Person> {
    {
        fields.put("name", new StringField("Name", false));
        fields.put("birthday", new DateField("Birthday", true));
        fields.put("hairColor", new EnumField("Hair color", false, Color.values()));
        fields.put("location", new ModelField<>("Location", new LocationSerializer()));
    }

    @Override
    public Person toValue() {
        if (!isValid())
            throw new NotValidatedYetException("You should run validate() method first.");
        return new Person((String) fields.get("name").toValue(),
                          (java.util.Date) fields.get("birthday").toValue(),
                          (Color) fields.get("hairColor").toValue(),
                          (Location) fields.get("location").toValue());
    }
}
