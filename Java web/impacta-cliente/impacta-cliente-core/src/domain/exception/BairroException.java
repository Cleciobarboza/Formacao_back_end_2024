package domain.exception;

public class BairroException extends Exception {

	private static final long serialVersionUID = -6801156121108080007L;

	public BairroException(String message) {
		super(message);
	}

	public BairroException(Throwable cause) {
		super(cause);
	}

	public BairroException(String message, Throwable cause) {
		super(message, cause);
	}
}
