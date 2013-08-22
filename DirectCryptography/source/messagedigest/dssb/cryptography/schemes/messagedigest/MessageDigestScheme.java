package dssb.cryptography.schemes.messagedigest;

import dssb.cryptography.Scheme;

/**
 * Cryptography scheme for message digest.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public enum MessageDigestScheme implements Scheme {
    
    /** Shorthand instance. */
    _,
    
    /** Static import instance. */
    MessageDigestScheme,
    
    /** Conventional instance. */
    INSTANCE;
    
    @Override
    public MessageDigestCryptographyBuilder createCryptographyBuilder() {
        return new MessageDigestCryptographyBuilder();
    }
    
}
