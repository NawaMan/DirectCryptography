package dssb.cryptography.schemes.rsa;

import dssb.cryptography.Scheme;

/**
 * RSA cryptography scheme.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public enum RsaScheme implements Scheme {
    
    /** Shorthand instance. */
    _,
    
    /** Static import instance. */
    Rsa,
    
    /** Semantic instance. */
    Scheme,
    
    /** Conventional instance. */
    INSTANCE;
    
    @Override
    public RsaCryptographyBuilder createCryptographyBuilder() {
        return new RsaCryptographyBuilder();
    }
    
}
