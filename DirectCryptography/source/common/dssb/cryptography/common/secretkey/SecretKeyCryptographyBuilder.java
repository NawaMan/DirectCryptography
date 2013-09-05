package dssb.cryptography.common.secretkey;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import dssb.cryptography.CryptographyBuilder;

/**
 * Common implementation for cryptography builder with a secret key.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public abstract class SecretKeyCryptographyBuilder implements CryptographyBuilder {
    
    /** The secret key. */
    private SecretKey secretKey;
    
    /** The secret key generator. */
    private volatile CryptographySecretKeyGenerator keyGenerator;
    
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
     * @param <_Builder_>
     *            the cryptography builder.
     * @param secretKey
     *            the secret key.
     * @return this cryptography builder.
     **/
    @SuppressWarnings("unchecked")
    protected <_Builder_ extends SecretKeyCryptographyBuilder> _Builder_ setSecretKey(
            final SecretKey secretKey) {
        this.secretKey = secretKey;
        return (_Builder_) this;
    }

    
    /**
     * User a newly generated key pair.
     **/
    protected void useNewKey() {
        final CryptographySecretKeyGenerator generator = this.getSecretKeyGenerator();
        if (generator == null) {
            return;
        }
        
        generator.useNewKey();
    }
    
    /**
     * User a newly generated secret key.
     * 
     * @param generator
     *            the secret key generator.
     **/
    protected void useNewKey(
            final KeyGenerator generator) {
        final SecretKey generatedSecretKey = generator.generateKey();
        this.setSecretKey(generatedSecretKey);
    }
    
    /**
     * Returns the secret key generator for cryptography.
     * 
     * @return the secret key generator for cryptography.
     **/
    protected final CryptographySecretKeyGenerator getSecretKeyGenerator() {
        if (this.keyGenerator != null) {
            return this.keyGenerator;
        }
        
        synchronized (this) {
            if (this.keyGenerator != null) {
                return this.keyGenerator;
            }
            
            this.keyGenerator = this.newCryptographySecretKeyGenerator();
            if (this.keyGenerator == null) {
                throw new UnsupportedOperationException();
            }
            
            return this.keyGenerator;
        }
    }
    
    /**
     * Returns a newly created secret key generator for cryptography.
     * 
     * @return a newly created secret key generator for cryptography.
     **/
    protected CryptographySecretKeyGenerator newCryptographySecretKeyGenerator() {
        return null;
    }
}
