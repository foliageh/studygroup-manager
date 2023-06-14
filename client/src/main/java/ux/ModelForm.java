package ux;

import models.Model;
import serialization.exceptions.FieldValidationException;
import serialization.fields.BaseField;
import serialization.fields.Field;
import serialization.fields.ModelField;
import serialization.serializers.Serializer;

public class ModelForm<T extends Model> {
    private final Serializer<T> serializer;
    private byte inputFieldLevel = -1;

    public ModelForm(Serializer<T> serializer) {
        this.serializer = serializer;
    }

    public T fill() {
        setupFieldValues(serializer);
        serializer.validate();
        return serializer.toValue();
    }

    private void setupFieldValues(Serializer<?> serializer) {
        inputFieldLevel++;
        for (BaseField<?> inputField : serializer.fields.values()) {
            while (true) {
                System.out.print("  ".repeat(inputFieldLevel) + inputField.verboseName() + " "+inputField.properties() + ": ");
                try {
                    if (inputField instanceof ModelField) {
                        System.out.println();
                        setupFieldValues(((ModelField<?>) inputField).getSerializer());
                        inputField.validate();
                    } else {
                        String input = ConsoleInput.nextLine();
                        ((Field<?>) inputField).setInitValue(input);
                        inputField.validate();
                    }
                    break;
                } catch (FieldValidationException e) {
                    System.out.println("!!! " + e.getMessage());
                }
            }
        }
        inputFieldLevel--;
    }
}
