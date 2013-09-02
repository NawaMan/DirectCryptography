package dssb.cryptography.common.secretkey;

import javax.crypto.SecretKey;

import dssb.cryptography.Scheme;
import dssb.cryptography.common.AbstractCommonCryptography;

/**
 * Abstract implementation for cryptography with secret key.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class AbstractSecretKeyCryptography extends AbstractCommonCryptography {
    
    /** The secret key. */
    private final SecretKey secretKey;
    
    /**
     * Constructor.
     * 
     * @param scheme
     *            the cryptography scheme.
     * @param secretKey
     *            the secret key.
     */
    public AbstractSecretKeyCryptography(
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
