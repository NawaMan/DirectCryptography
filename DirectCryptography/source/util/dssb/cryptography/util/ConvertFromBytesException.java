package dssb.cryptography.util;


/**
 * This exception is thrown when there is a problem converting data from a byte array.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class ConvertFromBytesException extends DataConversionException {
    
    /** The version unique ID for serialization. */
    private static final long serialVersionUID = -4891199183517409234L;
    
    /**
     * Constructs the exception.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the cause.
     */
    public ConvertFromBytesException(
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
    public ConvertFromBytesException(
            final String message) {
        super(message, null);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public ConvertFromBytesException(
            final Throwable cause) {
        super(null, cause);
    }
    
    /**
     * Constructs the exception.
     */
    public ConvertFromBytesException() {
        super(null, null);
    }
    
}
