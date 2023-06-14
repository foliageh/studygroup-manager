package serialization.fields;

import serialization.exceptions.NotValidatedYetException;

import java.util.ArrayList;

public abstract class BaseField<T> {
    private final String verboseName;
    protected final ArrayList<String> properties = new ArrayList<>();
    protected T value;
    protected boolean isValid;

    public BaseField(String verboseName) {
        this.verboseName = verboseName;
    }

    public final String verboseName() {
        return verboseName;
    }

    public final String properties() {
        return properties.isEmpty() ? "" : properties.toString();
    }

    public final boolean isValid() {
        return isValid;
    }

    public final T toValue() {
        if (!isValid)
            throw new NotValidatedYetException("You should run validate() method first.");
        return value;
    }

    public abstract boolean validate();
}
