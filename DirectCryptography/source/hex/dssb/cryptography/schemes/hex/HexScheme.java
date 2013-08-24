package dssb.cryptography.schemes.hex;

import dssb.cryptography.Scheme;

/**
 * Cryptography scheme for message digest.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public enum HexScheme implements Scheme {
    
    /** Shorthand instance. */
    _,
    
    /** Static import instance. */
    Hex,
    
    /** Conventional instance. */
    INSTANCE;
    
    @Override
    public HexCryptographyBuilder createCryptographyBuilder() {
        return new HexCryptographyBuilder();
    }
    
}
