package serialization.fields;

import serialization.exceptions.InvalidFieldFormatException;

public class DoubleField extends Field<Double> {
    private Double lowerBound;

    public DoubleField(String verboseName, boolean allowNull) {
        super(verboseName, allowNull);
        properties.add("double");
    }

    public DoubleField(String verboseName, boolean allowNull, Double lowerBound) {
        this(verboseName, allowNull);
        this.lowerBound = lowerBound;
        properties.add("must be greater than " + lowerBound);
    }

    @Override
    public boolean validate() {
        if (super.validate()) return true;
        try {
            value = Double.parseDouble(initValue.replace(',', '.'));
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
