package serialization.fields;

import serialization.exceptions.InvalidFieldFormatException;

public class LongField extends Field<Long> {
    private Long lowerBound;

    public LongField(String verboseName, boolean allowNull) {
        super(verboseName, allowNull);
        properties.add("long");
    }

    public LongField(String verboseName, boolean allowNull, Long lowerBound) {
        this(verboseName, allowNull);
        this.lowerBound = lowerBound;
        properties.add("must be greater than " + lowerBound);
    }

    @Override
    public boolean validate() {
        if (super.validate()) return true;
        try {
            value = Long.parseLong(initValue);
        } catch (NumberFormatException e) {
            throw new InvalidFieldFormatException("Invalid field value format");
        }
        if (lowerBound != null && value <= lowerBound) {
            throw new InvalidFieldFormatException("Number must be greater than " + lowerBound);
        }
        isValid = true;
        return true;
    }
}
