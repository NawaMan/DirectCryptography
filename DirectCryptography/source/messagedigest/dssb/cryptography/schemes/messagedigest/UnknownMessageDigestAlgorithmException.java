package dssb.cryptography.schemes.messagedigest;

import dssb.cryptography.javascrypto.UnknownAlgorithmException;

/**
 * This exception is thrown the algorithm for a message digest is not known.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class UnknownMessageDigestAlgorithmException extends UnknownAlgorithmException {
    
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
    public UnknownMessageDigestAlgorithmException(
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
    public UnknownMessageDigestAlgorithmException(
            final String message) {
        super(message, null);
    }
    
    /**
     * Constructs the exception.
     * 
     * @param cause
     *            the cause.
     */
    public UnknownMessageDigestAlgorithmException(
            final Throwable cause) {
        super(null, cause);
    }
    
    /**
     * Constructs the exception.
     */
    public UnknownMessageDigestAlgorithmException() {
        super(null, null);
    }
    
}
