package dssb.cryptography.common.keypair;

import java.security.KeyPair;

/**
 * Keypair generator for a cryptography.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public abstract class CryptographyKeyPairGenerator {
    
    /** The cryptography builder. */
    private final AbstractCommonKeyPairCryptographyBuilder cryptographyBuilder;
    
    /** They key generator. */
    private final KeyPairGenerator keyPairGenerator;
    
    /**
     * Constructor.
     * 
     * @param cryptographyBuilder
     *            the cryptography builder.
     * @param keyPairGenerator
     *            the key pair generator.
     */
    protected CryptographyKeyPairGenerator(
            final AbstractCommonKeyPairCryptographyBuilder cryptographyBuilder,
            final KeyPairGenerator keyPairGenerator) {
        if (cryptographyBuilder == null) {
            throw new NullPointerException();
        }
        if (keyPairGenerator == null) {
            throw new NullPointerException();
        }
        
        this.cryptographyBuilder = cryptographyBuilder;
        this.keyPairGenerator = keyPairGenerator;
    }
    
    /**
     * Generate a new key pair.
     */
    public void useNewKeyPair() {
        final KeyPair keyPair = this.keyPairGenerator.generate();
        this.cryptographyBuilder.setKeyPair(keyPair);
    }
    
}
