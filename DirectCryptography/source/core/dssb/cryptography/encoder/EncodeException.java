package dssb.cryptography.encoder;

/**
 * This exception is thrown when there is a problem encoding the data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class EncodeException extends RuntimeException {
    
    /** The version unique ID for serialization. */
    private static final long serialVersionUID = 4255163902582381360L;
    
    /**
     * Constructs the exception.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the cause.
     */
    public EncodeException(
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
    public EncodeException(
            final String message) {
        super(message, null);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public EncodeException(
            final Throwable cause) {
        super(null, cause);
    }
    
    /**
     * Constructs the exception.
     */
    public EncodeException() {
        super(null, null);
    }
    
}
