package app;

public class AdminServiceException extends Exception{
	
	public AdminServiceException(String message) {
        super(message);
    }
    public AdminServiceException(String message, Exception cause) {
        super(message, cause);
    }
}



