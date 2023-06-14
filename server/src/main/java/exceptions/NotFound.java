package exceptions;

public class NotFound extends ServerException {
    public NotFound(String errorMessage) {
        super(errorMessage);
    }
}
