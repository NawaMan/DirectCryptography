package dssb.cryptography;


/**
 * Cryptography scheme.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Scheme {
    
    /**
     * Creates and return a new cryptography builder.
     * 
     * @return the builder.
     */
    public CryptographyBuilder createCryptographyBuilder();
    
}
