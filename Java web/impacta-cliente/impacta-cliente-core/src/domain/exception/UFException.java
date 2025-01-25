package domain.exception;

public class UFException extends Exception {

    private static final long serialVersionUID = -1302040970015604171L;

    public UFException(String message) {
        super(message);
    }

    public UFException(Throwable cause) {
        super(cause);
    }

    public UFException(String message, Throwable cause) {
        super(message, cause);
    }
}
