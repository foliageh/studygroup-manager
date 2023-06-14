package serialization.fields;

import serialization.exceptions.InvalidFieldFormatException;

public class IntegerField extends Field<Integer> {
    private Integer lowerBound;

    public IntegerField(String verboseName, boolean allowNull) {
        super(verboseName, allowNull);
        properties.add("integer");
    }

    public IntegerField(String verboseName, boolean allowNull, Integer lowerBound) {
        this(verboseName, allowNull);
        this.lowerBound = lowerBound;
        properties.add("must be greater than " + lowerBound);
    }

    @Override
    public boolean validate() {
        if (super.validate()) return true;
        try {
            value = Integer.parseInt(initValue);
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
