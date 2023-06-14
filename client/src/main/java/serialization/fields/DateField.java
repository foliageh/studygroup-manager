package serialization.fields;

import serialization.exceptions.InvalidFieldFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateField extends Field<Date> {
    private final SimpleDateFormat dateFormat;
    {
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
    }

    public DateField(String verboseName, boolean allowNull) {
        super(verboseName, allowNull);
        properties.add("must be in 'dd.MM.yyyy' format");
    }

    @Override
    public boolean validate() {
        if (super.validate()) return true;
        try {
            value = dateFormat.parse(initValue);
            isValid = true;
            if (!initValue.matches("\\d{2}\\.\\d{2}\\.\\d{4}"))
                throw new InvalidFieldFormatException("");
        } catch (ParseException | InvalidFieldFormatException | NumberFormatException e) {
            throw new InvalidFieldFormatException("Invalid date. Please, provide the correct date in 'dd.MM.yyyy' format");
        }
        return true;
    }
}
