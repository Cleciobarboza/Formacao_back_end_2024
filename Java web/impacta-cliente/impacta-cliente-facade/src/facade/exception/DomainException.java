package facade.exception;

public class DomainException extends Exception {

    private static final long serialVersionUID = 6257801946873648038L;

    public DomainException(String message) {
		super(message);
	}

	public DomainException(Throwable cause) {
		super(cause);
	}

	public DomainException(String message, Throwable cause) {
		super(message, cause);
	}
}
