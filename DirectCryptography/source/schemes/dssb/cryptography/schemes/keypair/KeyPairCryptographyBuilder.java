package dssb.cryptography.schemes.keypair;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.CryptographyBuilder;

abstract public class KeyPairCryptographyBuilder implements CryptographyBuilder {
    
    private PublicKey publicKey = null;
    private PrivateKey privateKey = null;
    private KeyPairGenerator generator = null;
    
    public void setKeyPair(
            final KeyPair keyPair) {
        this.publicKey = (keyPair == null)
                ? null
                : keyPair.getPublic();
        this.privateKey = (keyPair == null)
                ? null
                : keyPair.getPrivate();
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
    
    public PublicKey getPublicKey() {
        return this.publicKey;
    }
    
    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }
    
    public KeyPair getKeyPair() {
        return new KeyPair(this.publicKey, this.privateKey);
    }
        
    abstract protected int getDefaultKeySize();
    
    abstract protected String getDefaultAlgorithm();
    
}
