package dssb.cryptography.common.secretkey;

import javax.crypto.SecretKey;

import dssb.cryptography.Scheme;
import dssb.cryptography.common.AbstractCryptography;

/**
 * Common implementation for cryptography with a secret key.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public abstract class SecretKeyCryptography extends AbstractCryptography {
    
    /** The secret key. */
    private final SecretKey secretKey;
    
    /**
     * Constructor.
     * 
     * @param scheme
     *            the scheme.
     * @param secretKey
     *            the secret key.
     */
    protected SecretKeyCryptography(
            final Scheme scheme,
            final SecretKey secretKey) {
        super(scheme);
        this.secretKey = secretKey;
    }
    
    /**
     * Returns the secret key.
     * 
     * @return the secret key.
     **/
    protected SecretKey getSecretKey() {
        return this.secretKey;
    }
    
}
