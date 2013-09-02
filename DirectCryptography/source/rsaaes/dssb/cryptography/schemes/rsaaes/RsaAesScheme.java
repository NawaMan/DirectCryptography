package dssb.cryptography.schemes.rsaaes;

import dssb.cryptography.Scheme;

/**
 * Cryptography scheme for RSA+AES.
 * 
 * - Generate a new AES key and use it to encrypt the data.
 * - Use given key pair to encrypt the AES key generated.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public enum RsaAesScheme implements Scheme {
    
    /** Shorthand instance. */
    _,
    
    /** Static import instance. */
    RsaAes,
    
    /** Semantic instance. */
    Scheme,
    
    /** Conventional instance. */
    INSTANCE;
    
    @Override
    public RsaAesCryptographyBuilder createCryptographyBuilder() {
        return new RsaAesCryptographyBuilder();
    }
    
}
