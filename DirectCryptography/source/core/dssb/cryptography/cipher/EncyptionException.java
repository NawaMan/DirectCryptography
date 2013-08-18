package dssb.cryptography.cipher;

/**
 * This exception is thrown when there is a problem encrypting data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class EncyptionException extends RuntimeException {
    
    /** The version unique ID for serialization. */
    private static final long serialVersionUID = -8287271644181776179L;
    
    /**
     * Constructs the exception.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the cause.
     */
    public EncyptionException(
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
    public EncyptionException(
            final String message) {
        super(message, null);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public EncyptionException(
            final Throwable cause) {
        super(null, cause);
    }
    
    /**
     * Constructs the exception.
     */
    public EncyptionException() {
        super(null, null);
    }
    
}
