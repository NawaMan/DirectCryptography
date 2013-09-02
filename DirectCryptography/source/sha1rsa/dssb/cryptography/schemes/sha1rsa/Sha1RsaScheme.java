package dssb.cryptography.schemes.sha1rsa;

import dssb.cryptography.Scheme;

/**
 * Cryptography scheme for SHA1+RSA.
 * 
 * NOTE: Really love to generalize this ... but I don't think I understand this enough.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public enum Sha1RsaScheme implements Scheme {
    
    /** Shorthand instance. */
    _,
    
    /** Static import instance. */
    Sha1Rsa,
    
    /** Semantic instance. */
    Scheme,
    
    /** Conventional instance. */
    INSTANCE;
    
    @Override
    public Sha1RsaCryptographyBuilder createCryptographyBuilder() {
        return new Sha1RsaCryptographyBuilder();
    }
    
}
