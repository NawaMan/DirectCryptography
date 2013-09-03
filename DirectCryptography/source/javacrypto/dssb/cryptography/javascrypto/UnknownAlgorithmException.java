package dssb.cryptography.javascrypto;

import dssb.cryptography.CryptographyConfigurationException;

/**
 * This exception is thrown the algorithm is not known.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class UnknownAlgorithmException extends CryptographyConfigurationException {
    
    /** The version unique ID for serialization. */
    private static final long serialVersionUID = -6041527887172720074L;
    
    /**
     * Constructs the exception.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the cause.
     */
    public UnknownAlgorithmException(
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
    public UnknownAlgorithmException(
            final String message) {
        super(message, null);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public UnknownAlgorithmException(
            final Throwable cause) {
        super(null, cause);
    }
    
    /**
     * Constructs the exception.
     */
    public UnknownAlgorithmException() {
        super(null, null);
    }
    
}
