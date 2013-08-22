package dssb.cryptography.hasher;

/**
 * This exception is thrown when there is a problem calculating hash of the data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class HashException extends RuntimeException {
    
    /** The version unique ID for serialization. */
    private static final long serialVersionUID = -2818662710171402508L;
    
    /**
     * Constructs the exception.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the cause.
     */
    public HashException(
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
    public HashException(
            final String message) {
        super(message, null);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public HashException(
            final Throwable cause) {
        super(null, cause);
    }
    
    /**
     * Constructs the exception.
     */
    public HashException() {
        super(null, null);
    }
    
}
