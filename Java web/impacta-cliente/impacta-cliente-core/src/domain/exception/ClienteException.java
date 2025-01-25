package domain.exception;

public class ClienteException extends Exception {

    private static final long serialVersionUID = -2687729872931161171L;

    public ClienteException(String message) {
        super(message);
    }

    public ClienteException(Throwable cause) {
        super(cause);
    }

    public ClienteException(String message, Throwable cause) {
        super(message, cause);
    }
}
