package repository.jdbc;

public class MySQLException extends Exception {

	private static final long serialVersionUID = 4375829992859568917L;

	public MySQLException(String message, Throwable cause) {
		super(message, cause);
	}

	public MySQLException(Throwable cause) {
		super(cause);
	}
}