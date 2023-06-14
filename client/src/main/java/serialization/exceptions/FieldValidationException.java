package serialization.exceptions;

public class FieldValidationException extends RuntimeException {
    public FieldValidationException(String errorMessage) {
        super(errorMessage);
    }
}
