package dssb.cryptography;

/**
 * This exception is thrown when configuration for the {@link Cryptography} (via a {@link CryptographyBuilder}) is not
 * valid.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class CryptographyConfigurationException extends RuntimeException {
    
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
    public CryptographyConfigurationException(
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
    public CryptographyConfigurationException(
            final String message) {
        super(message, null);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public CryptographyConfigurationException(
            final Throwable cause) {
        super(null, cause);
    }
    
    /**
     * Constructs the exception.
     */
    public CryptographyConfigurationException() {
        super(null, null);
    }
    
}
