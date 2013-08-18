package dssb.cryptography.signature;

/**
 * This exception is thrown when there is a problem verifying singed data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class VerifyException extends RuntimeException {
    
    /** The version unique ID for serialization. */
    private static final long serialVersionUID = -5006762246315609468L;
    
    /**
     * Constructs the exception.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the cause.
     */
    public VerifyException(
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
    public VerifyException(
            final String message) {
        super(message, null);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public VerifyException(
            final Throwable cause) {
        super(null, cause);
    }
    
    /**
     * Constructs the exception.
     */
    public VerifyException() {
        super(null, null);
    }
    
}
