package serialization.exceptions;

public class NotValidatedYetException extends RuntimeException {
    public NotValidatedYetException(String errorMessage) {
        super(errorMessage);
    }
}
