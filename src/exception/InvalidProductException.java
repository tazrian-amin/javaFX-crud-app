package exception;

/**
 * Custom exception for invalid product data
 * Demonstrates: Exception Handling - Custom Exception Classes
 */
public class InvalidProductException extends Exception {
    
    // Constructor with message
    public InvalidProductException(String message) {
        super(message);
    }
    
    // Constructor with message and cause
    public InvalidProductException(String message, Throwable cause) {
        super(message, cause);
    }
}

