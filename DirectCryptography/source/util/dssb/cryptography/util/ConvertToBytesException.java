package dssb.cryptography.util;

/**
 * This exception is thrown when there is a problem converting data to a byte array.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class ConvertToBytesException extends DataConversionException {
    
    /** The version unique ID for serialization. */
    private static final long serialVersionUID = 7459173043619659897L;
    
    /**
     * Constructs the exception.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the cause.
     */
    public ConvertToBytesException(
            final String message,
            final Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param message
     *            the detail message.
     */
    public ConvertToBytesException(
            final String message) {
        super(message, null);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public ConvertToBytesException(
            final Throwable cause) {
        super(null, cause);
    }
    
    /**
     * Constructs the exception.
     */
    public ConvertToBytesException() {
        super(null, null);
    }
    
}
