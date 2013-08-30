package dssb.cryptography.common.secretkey;

import javax.crypto.SecretKey;

import dssb.cryptography.CryptographyBuilder;

/**
 * Abstract implementation for cryptography builder that use secret key.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public abstract class AbstractSecretKeyCryptographyBuilder implements CryptographyBuilder {
    
    /** The secret key. */
    private SecretKey secretKey;
    
    /**
     * Returns the secret key.
     * 
     * @return the secret key.
     **/
    public SecretKey getSecretKey() {
        return this.secretKey;
    }
    
    /**
     * Change the secret key.
     * 
     * @param secretKey
     *            the new secret key.
     **/
    protected void setSecretKey(
            final SecretKey secretKey) {
        this.secretKey = secretKey;
    }
    
}
