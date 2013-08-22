package dssb.cryptography;

/**
 * This exception is thrown when building a cryptography fail.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class CryptographyBuildFailException extends RuntimeException {
    
    /** The version unique ID for serialization. */
    private static final long serialVersionUID = -8027255610734911015L;

    /**
     * Constructs the exception.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the cause.
     */
    public CryptographyBuildFailException(
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
    public CryptographyBuildFailException(
            final String message) {
        super(message, null);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public CryptographyBuildFailException(
            final Throwable cause) {
        super(null, cause);
    }
    
    /**
     * Constructs the exception.
     */
    public CryptographyBuildFailException() {
        super(null, null);
    }
    
}
