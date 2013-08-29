package dssb.cryptography.schemes.rsa;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.common.keypair.AbstractCommonKeyPairCryptographyBuilder;
import dssb.cryptography.common.keypair.KeyPairGenerator;

public class RsaCryptographyBuilder extends AbstractCommonKeyPairCryptographyBuilder {
    
    public static final int DEFAULT_KEYSIZE = 1024;
    public static final String DEFAULT_ALGORITHM = "RSA";
    
    @Override
    public RsaCryptography newCryptography() {
        final PrivateKey privateKey = this.getPrivateKey();
        final PublicKey publicKey = this.getPublicKey();
        final RsaCryptography cryptography = new RsaCryptography(privateKey, publicKey);
        return cryptography;
    }
    
    public void useNewKeyPair() {
        final String algorithm = this.getDefaultAlgorithm();
        final int keysize = this.getDefaultKeySize();
        this.useNewKeyPair(algorithm, keysize);
    }
    
    public void useNewKeyPair(
            final String algorithm,
            final int keysize) {
        final KeyPairGenerator generator = new KeyPairGenerator.Simple(algorithm, keysize);
        this.useNewKeyPair(generator);
    }
    
    public void useNewKeyPair(
            final KeyPairGenerator generator) {
        final KeyPair keyPair = generator.generate();
        this.setKeyPair(keyPair);
    }
    
    protected int getDefaultKeySize() {
        return DEFAULT_KEYSIZE;
    }
    
    protected String getDefaultAlgorithm() {
        return DEFAULT_ALGORITHM;
    }
}
