package domain.exception;

public class LogradouroException extends Exception {

    private static final long serialVersionUID = 1298229803478048251L;

    public LogradouroException(String message) {
        super(message);
    }

    public LogradouroException(Throwable cause) {
        super(cause);
    }

    public LogradouroException(String message, Throwable cause) {
        super(message, cause);
    }
}
