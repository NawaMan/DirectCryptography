package dssb.cryptography.common.secretkey;

import javax.crypto.SecretKey;

import dssb.cryptography.CryptographyBuilder;

/**
 * Common implementation for {@link CryptographyBuilder} with a secret key.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
abstract public class AbstractCommonSecretKeyCryptographyBuilder implements CryptographyBuilder {
    
    /** The secret key. */
    private SecretKey secretKey;
    
    /**
     * Returns the secret key.
     * 
     * @return the secret key.
     */
    public SecretKey getSecretKey() {
        return this.secretKey;
    }
    
    /**
     * Change the secret key.
     * 
     * @param secretKey
     *            the secret key.
     **/
    protected void setSecretKey(
            final SecretKey secretKey) {
        this.secretKey = secretKey;
    }
    
}
