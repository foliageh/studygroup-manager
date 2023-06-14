package serialization.fields;

import models.Model;
import serialization.serializers.Serializer;

public class ModelField<T extends Model> extends BaseField<T> {
    private final Serializer<T> serializer;

    public ModelField(String verboseName, Serializer<T> serializer) {
        super(verboseName);
        this.serializer = serializer;
    }

    public Serializer<T> getSerializer() {
        return serializer;
    }

    @Override
    public boolean validate() {
        serializer.validate();
        value = serializer.toValue();
        isValid = true;
        return true;
    }
}
