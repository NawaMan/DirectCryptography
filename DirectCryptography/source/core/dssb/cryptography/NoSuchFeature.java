package dssb.cryptography;

/**
 * This exception is thrown when there is no required feature.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class NoSuchFeature extends RuntimeException {
    
    /** The version unique ID for serialization. */
    private static final long serialVersionUID = 5497651544024634052L;
    
    /**
     * Constructs the exception.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the cause.
     */
    public NoSuchFeature(
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
    public NoSuchFeature(
            final String message) {
        super(message, null);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public NoSuchFeature(
            final Throwable cause) {
        super(null, cause);
    }
    
    /**
     * Constructs the exception.
     */
    public NoSuchFeature() {
        super(null, null);
    }
    
}
