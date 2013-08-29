package dssb.cryptography.common.keypair;

import java.security.KeyPair;

abstract public class CryptographyKeyPairGenerator {
    
    private final AbstractCommonKeyPairCryptographyBuilder cryptographyBuilder;
    
    private final KeyPairGenerator keyPairGenerator;
    
    public CryptographyKeyPairGenerator(
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
    
    public void useNewKeyPair() {
        final KeyPair keyPair = this.keyPairGenerator.generate();
        this.cryptographyBuilder.setKeyPair(keyPair);
    }
    
}
