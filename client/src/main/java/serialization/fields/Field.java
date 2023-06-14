package serialization.fields;

import serialization.exceptions.FieldNullValueException;

public abstract class Field<T> extends BaseField<T> {
    protected String initValue;
    private final boolean allowNull;

    public Field(String verboseName, boolean allowNull) {
        super(verboseName);
        this.allowNull = allowNull;
        properties.add(allowNull ? "can be empty" : "can't be empty");
    }

    public final void setInitValue(String initValue) {
        this.initValue = initValue;
    }

    @Override
    public boolean validate() {
        if (initValue == null || initValue.isBlank()) {
            if (allowNull) {
                value = null;
                isValid = true;
            }
            else {
                throw new FieldNullValueException("This field can't be empty");
            }
        }
        return isValid;
    }
}
