package serialization.serializers;

import models.*;
import serialization.exceptions.NotValidatedYetException;
import serialization.fields.EnumField;
import serialization.fields.LongField;
import serialization.fields.ModelField;
import serialization.fields.StringField;

public class StudyGroupSerializer extends Serializer<StudyGroup> {
    {
        fields.put("name", new StringField("Name", false));
        fields.put("coordinates", new ModelField<>("Coordinates", new CoordinatesSerializer()));
        fields.put("studentsCount", new LongField("Students count", true, -1L));
        fields.put("formOfEducation", new EnumField("Form of education", false, FormOfEducation.values()));
        fields.put("semesterEnum", new EnumField("Semester", false, Semester.values()));
        fields.put("groupAdmin", new ModelField<>("Group admin", new PersonSerializer()));
    }

    @Override
    public StudyGroup toValue() {
        if (!isValid())
            throw new NotValidatedYetException("You should run validate() method first.");
        return new StudyGroup((String) fields.get("name").toValue(),
                              (Coordinates) fields.get("coordinates").toValue(),
                              (Long) fields.get("studentsCount").toValue(),
                              (FormOfEducation) fields.get("formOfEducation").toValue(),
                              (Semester) fields.get("semesterEnum").toValue(),
                              (Person) fields.get("groupAdmin").toValue());
    }
}
