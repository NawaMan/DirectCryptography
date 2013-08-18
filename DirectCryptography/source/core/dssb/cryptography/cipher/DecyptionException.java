package dssb.cryptography.cipher;

/**
 * This exception is thrown when there is a problem decrypting data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class DecyptionException extends RuntimeException {
    
    /** The version unique ID for serialization. */
    private static final long serialVersionUID = 9175465886091744812L;
    
    /**
     * Constructs the exception.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the cause.
     */
    public DecyptionException(
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
    public DecyptionException(
            final String message) {
        super(message, null);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public DecyptionException(
            final Throwable cause) {
        super(null, cause);
    }
    
    /**
     * Constructs the exception.
     */
    public DecyptionException() {
        super(null, null);
    }
    
}
