package dssb.cryptography.common.secretkey;

import javax.crypto.SecretKey;


/**
 * Keypair generator for a cryptography.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public abstract class CryptographySecretKeyGenerator {
    
    /** The cryptography builder. */
    private final SecretKeyCryptographyBuilder cryptographyBuilder;
    
    /** They key generator. */
    private final SecretKeyGenerator keyGenerator;
    
    /**
     * Constructor.
     * 
     * @param cryptographyBuilder
     *            the cryptography builder.
     * @param keyGenerator
     *            the key pair generator.
     */
    protected CryptographySecretKeyGenerator(
            final SecretKeyCryptographyBuilder cryptographyBuilder,
            final SecretKeyGenerator keyGenerator) {
        if (cryptographyBuilder == null) {
            throw new NullPointerException();
        }
        if (keyGenerator == null) {
            throw new NullPointerException();
        }
        
        this.cryptographyBuilder = cryptographyBuilder;
        this.keyGenerator = keyGenerator;
    }
    
    /**
     * Returns the key generator.
     * 
     * @return the key generator.
     **/
    protected SecretKeyGenerator getKeyGenerator() {
        return this.keyGenerator;
    }
    
    /**
     * Generate a new secret key.
     */
    public void useNewKey() {
        final SecretKey secretKey = this.keyGenerator.generate();
        this.cryptographyBuilder.setSecretKey(secretKey);
    }
    
}
