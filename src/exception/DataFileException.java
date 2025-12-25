package exception;

/**
 * Custom exception for file I/O operations
 * Demonstrates: Exception Handling - Custom Exception Classes
 */
public class DataFileException extends Exception {
    
    // Constructor with message
    public DataFileException(String message) {
        super(message);
    }
    
    // Constructor with message and cause
    public DataFileException(String message, Throwable cause) {
        super(message, cause);
    }
}

