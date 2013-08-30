package dssb.cryptography.common.keypair;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.CryptographyBuilder;

/**
 * Cryptography builder that use key pair.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public abstract class AbstractKeyPairCryptographyBuilder implements CryptographyBuilder {
    
    /** The public key. */
    private PublicKey publicKey = null;
    /** The private key. */
    private PrivateKey privateKey = null;
    
    /**
     * Change the key pair.
     * 
     * @param keyPair
     *            the key pair.
     **/
    public void setKeyPair(
            final KeyPair keyPair) {
        this.publicKey = (keyPair == null)
                ? null
                : keyPair.getPublic();
        this.privateKey = (keyPair == null)
                ? null
                : keyPair.getPrivate();
    }
    
    /**
     * Generate and use a new key pair.
     */
    public void useNewKeyPair() {
        final String algorithm = this.getDefaultAlgorithm();
        final int keysize = this.getDefaultKeySize();
        this.useNewKeyPair(algorithm, keysize);
    }
    
    /**
     * Generate and use a new key pair.
     * 
     * @param algorithm
     *            the algorithm name.
     * @param keysize
     *            the key size.
     */
    public void useNewKeyPair(
            final String algorithm,
            final int keysize) {
        final KeyPairGenerator keyPairGenerator = new KeyPairGenerator.JavaCrypto(algorithm, keysize);
        this.useNewKeyPair(keyPairGenerator);
    }
    
    /**
     * Generate and use a new key pair.
     * 
     * @param generator
     *            the keypair generator.
     */
    public void useNewKeyPair(
            final KeyPairGenerator generator) {
        final KeyPair keyPair = generator.generate();
        this.setKeyPair(keyPair);
    }
    
    /**
     * Returns the public key.
     * 
     * @return the public key.
     */
    public PublicKey getPublicKey() {
        return this.publicKey;
    }
    
    /**
     * Returns the private key.
     * 
     * @return the private key.
     */
    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }
    
    /**
     * Returns the keypair.
     * 
     * @return the keypair.
     */
    public KeyPair getKeyPair() {
        return new KeyPair(this.publicKey, this.privateKey);
    }
    
    /**
     * Returns the default key size.
     * 
     * @return the default key size.
     **/
    protected abstract int getDefaultKeySize();
    
    /**
     * Returns the default algorithm name.
     * 
     * @return the default algorithm name.
     **/
    protected String getDefaultAlgorithm() {
        return KeyPairGenerator.JavaCrypto.DEFAULT_RANDOM_ALGORITHM;
    }
    
}
