package serialization.fields;

import serialization.exceptions.InvalidFieldFormatException;

import java.util.Arrays;

public class EnumField extends Field<Enum<?>> {
    private final Enum<?>[] choices;

    public EnumField(String verboseName, boolean allowNull, Enum<?>[] choices) {
        super(verboseName, allowNull);
        this.choices = choices;
        properties.add(Arrays.toString(choices));
    }

    @Override
    public boolean validate() {
        if (super.validate()) return true;
        for (Enum<?> v : choices) {
            if (v.name().equalsIgnoreCase(initValue) || ((Integer) v.ordinal()).toString().equals(initValue)) {
                value = v;
                isValid = true;
                return true;
            }
        }
        throw new InvalidFieldFormatException("The value of this field must be in " + Arrays.toString(choices));
    }
}
