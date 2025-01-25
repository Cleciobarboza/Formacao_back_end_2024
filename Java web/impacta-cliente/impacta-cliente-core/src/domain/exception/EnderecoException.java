package domain.exception;

public class EnderecoException extends Exception {

    private static final long serialVersionUID = -8916682779532822591L;

    public EnderecoException(String message) {
        super(message);
    }

    public EnderecoException(Throwable cause) {
        super(cause);
    }

    public EnderecoException(String message, Throwable cause) {
        super(message, cause);
    }
}
