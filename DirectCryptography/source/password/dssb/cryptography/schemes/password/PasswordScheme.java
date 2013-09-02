package dssb.cryptography.schemes.password;

import dssb.cryptography.Scheme;

/**
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public enum PasswordScheme implements Scheme {
    
    /** Shorthand instance. */
    _,
    
    /** Static import instance. */
    Password,
    
    /** Semantic instance. */
    Scheme,
    
    /** Conventional instance. */
    INSTANCE;
    
    @Override
    public PasswordCryptographyBuilder createCryptographyBuilder() {
        return new PasswordCryptographyBuilder();
    }
    
}
