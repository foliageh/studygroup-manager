package serialization.serializers;

import serialization.fields.BaseField;

import java.util.LinkedHashMap;

public abstract class Serializer<T> {
    public final LinkedHashMap<String, BaseField<?>> fields = new LinkedHashMap<>();
    private boolean isValid;

    public boolean isValid() {
        return isValid;
    }

    public void validate() {
        fields.values().forEach(BaseField::validate);
        isValid = true;
    }

    public abstract T toValue();
}
