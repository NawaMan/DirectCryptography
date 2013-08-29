package dssb.cryptography.encoder;

/**
 * This exception is thrown when there is a problem decoding the data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class DecodeException extends RuntimeException {
    
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
    public DecodeException(
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
    public DecodeException(
            final String message) {
        super(message, null);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public DecodeException(
            final Throwable cause) {
        super(null, cause);
    }
    
    /**
     * Constructs the exception.
     */
    public DecodeException() {
        super(null, null);
    }
    
}
