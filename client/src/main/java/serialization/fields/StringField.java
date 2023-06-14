package serialization.fields;

public class StringField extends Field<String> {
    public StringField(String verboseName, boolean allowNull) {
        super(verboseName, allowNull);
    }

    @Override
    public boolean validate() {
        if (super.validate()) return true;
        value = initValue;
        isValid = true;
        return true;
    }
}
