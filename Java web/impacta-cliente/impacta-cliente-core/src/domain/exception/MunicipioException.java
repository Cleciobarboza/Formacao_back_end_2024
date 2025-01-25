package domain.exception;

public class MunicipioException extends Exception {

    private static final long serialVersionUID = -1184834663935906705L;

    public MunicipioException(String message) {
        super(message);
    }

    public MunicipioException(Throwable cause) {
        super(cause);
    }

    public MunicipioException(String message, Throwable cause) {
        super(message, cause);
    }
}
