package dssb.cryptography.common.keypair;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.CryptographyBuilder;

/**
 * Common implementation for {@link CryptographyBuilder} with {@link KeyPair}.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
abstract public class AbstractCommonKeyPairCryptographyBuilder implements CryptographyBuilder {
    
    /** The private key. */
    private PrivateKey privateKey = null;
    
    /** The public key. */
    private PublicKey publicKey = null;
    
    /** The key pair generator. */
    volatile private CryptographyKeyPairGenerator keyPairGenerator;
    
    /**
     * Change the key pair.
     * 
     * @param keyPair
     *            the key pair.
     */
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
     * Returns the private key.
     * 
     * @return the private key.
     **/
    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }
    
    /**
     * Returns the public key.
     * 
     * @return the public key.
     **/
    public PublicKey getPublicKey() {
        return this.publicKey;
    }
    
    /**
     * Returns the key pair.
     * 
     * @return the key pair.
     **/
    public KeyPair getKeyPair() {
        return new KeyPair(this.publicKey, this.privateKey);
    }
    
    /**
     * Returns the key pair generator for cryptography.
     * 
     * @return the key pair generator for cryptography.
     **/
    protected CryptographyKeyPairGenerator getKeyPairGenerator() {
        if (this.keyPairGenerator != null) {
            return this.keyPairGenerator;
        }
        
        synchronized (this) {
            if (this.keyPairGenerator != null) {
                return this.keyPairGenerator;
            }
            
            this.keyPairGenerator = this.newKeyPairGenerator();
            if (this.keyPairGenerator == null) {
                // TODO - Throw appropriate exception.
                throw new NullPointerException();
            }
            
            return this.keyPairGenerator;
        }
    }
    
    /**
     * Returns a newly created key pair generator for cryptography.
     * 
     * @return a newly created key pair generator for cryptography.
     **/
    protected CryptographyKeyPairGenerator newKeyPairGenerator() {
        return null;
    }
    
}
