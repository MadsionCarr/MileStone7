package app;

public class AdminInvalidException extends Exception {
	/**
	 * AdminInvalidException Thrown when an invalid admin command is received.
	 */

	public AdminInvalidException(String message) {
		super(message);
	}

	public AdminInvalidException(String message, Exception cause) {
		super(message, cause);
	}

}
