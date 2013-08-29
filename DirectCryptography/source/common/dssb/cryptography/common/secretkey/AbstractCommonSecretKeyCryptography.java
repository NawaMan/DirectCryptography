package dssb.cryptography.common.secretkey;

import javax.crypto.SecretKey;

import dssb.cryptography.Cryptography;
import dssb.cryptography.Scheme;
import dssb.cryptography.common.AbstractCommonCryptography;

/**
 * Common implementation for {@link Cryptography} with a secret key.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
abstract public class AbstractCommonSecretKeyCryptography extends AbstractCommonCryptography {
    
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
    protected AbstractCommonSecretKeyCryptography(
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
