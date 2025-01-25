package domain.exception;

public class ContatoException extends Exception {

    private static final long serialVersionUID = 655413162343561262L;

    public ContatoException(String message) {
        super(message);
    }

    public ContatoException(Throwable cause) {
        super(cause);
    }

    public ContatoException(String message, Throwable cause) {
        super(message, cause);
    }
}
