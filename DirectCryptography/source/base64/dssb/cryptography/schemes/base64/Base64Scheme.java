package dssb.cryptography.schemes.base64;

import dssb.cryptography.Scheme;

/**
 * Cryptography scheme for message digest.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public enum Base64Scheme implements Scheme {
    
    /** Shorthand instance. */
    _,
    
    /** Static import instance. */
    Base64Scheme,
    
    /** Conventional instance. */
    INSTANCE;
    
    @Override
    public Base64CryptographyBuilder createCryptographyBuilder() {
        return new Base64CryptographyBuilder();
    }
    
}
