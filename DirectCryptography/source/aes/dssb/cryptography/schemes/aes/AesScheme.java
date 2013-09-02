package dssb.cryptography.schemes.aes;

import dssb.cryptography.Scheme;

/**
 * Cryptography scheme for AES.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public enum AesScheme implements Scheme {
    
    /** Shorthand instance. */
    _,
    
    /** Static import instance. */
    Aes,
    
    /** Semantic instance. */
    Scheme,
    
    /** Conventional instance. */
    INSTANCE;
    
    @Override
    public AesCryptographyBuilder createCryptographyBuilder() {
        return new AesCryptographyBuilder();
    }
    
}
