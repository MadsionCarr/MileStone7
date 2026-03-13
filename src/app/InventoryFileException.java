package app;

public class InventoryFileException extends Exception {
	public InventoryFileException(String message) { 
		super(message);	
	}
	
	public InventoryFileException(String message, Throwable cause) { 
		super(message, cause);}

}
