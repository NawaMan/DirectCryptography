package dssb.cryptography.signature;

/**
 * This exception is thrown when there is a problem signing data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class SignException extends RuntimeException {
    
    /** The version unique ID for serialization. */
    private static final long serialVersionUID = -2496531485275756001L;
    
    /**
     * Constructs the exception.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the cause.
     */
    public SignException(
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
    public SignException(
            final String message) {
        super(message, null);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public SignException(
            final Throwable cause) {
        super(null, cause);
    }
    
    /**
     * Constructs the exception.
     */
    public SignException() {
        super(null, null);
    }
    
}
