package dssb.cryptography.util;

/**
 * This exception is thrown when there is a problem converting data from one form to anther.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class DataConversionException extends RuntimeException {
    
    /** The version unique ID for serialization. */
    private static final long serialVersionUID = 5136112669036813787L;
    
    /**
     * Constructs the exception.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the cause.
     */
    public DataConversionException(
            final String message,
            final Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public DataConversionException(
            final Throwable cause) {
        super(cause);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param message
     *            the detail message.
     */
    public DataConversionException(
            final String message) {
        super(message);
    }
    
    /**
     * 
     * Constructs the exception.
     */
    public DataConversionException() {
        super();
    }
    
}
