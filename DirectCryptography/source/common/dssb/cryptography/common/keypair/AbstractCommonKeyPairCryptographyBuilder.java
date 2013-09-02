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
public abstract class AbstractCommonKeyPairCryptographyBuilder implements CryptographyBuilder {
    
    /** The private key. */
    private PrivateKey privateKey = null;
    
    /** The public key. */
    private PublicKey publicKey = null;
    
    /** The key pair generator. */
    private volatile CryptographyKeyPairGenerator keyPairGenerator;
    
    /**
     * Change the key pair.
     * 
     * @param keyPair
     *            the key pair.
     */
    protected void setKeyPair(
            final KeyPair keyPair) {
        this.publicKey = (keyPair == null)
                ? null
                : keyPair.getPublic();
        this.privateKey = (keyPair == null)
                ? null
                : keyPair.getPrivate();
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
     * 
     * Change the private key.
     * 
     * @param privateKey
     *            the private key.
     */
    protected void setPrivateKey(
            final PrivateKey privateKey) {
        this.privateKey = privateKey;
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
     * 
     * Change the public key.
     * 
     * @param publicKey
     *            the public key.
     */
    protected void setPublicKey(
            final PublicKey publicKey) {
        this.publicKey = publicKey;
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
     * User a newly generated key pair.
     **/
    protected void useNewKeyPair() {
        final CryptographyKeyPairGenerator generator = this.getKeyPairGenerator();
        if (generator == null) {
            return;
        }
        
        generator.useNewKeyPair();
    }
    
    /**
     * User a newly generated keypair.
     * 
     * @param generator
     *            the keypair generator.
     **/
    protected void useNewKeyPair(
            final KeyPairGenerator generator) {
        final KeyPair keyPair = generator.generate();
        this.setKeyPair(keyPair);
    }
    
    /**
     * Returns the key pair generator for cryptography.
     * 
     * @return the key pair generator for cryptography.
     **/
    protected final CryptographyKeyPairGenerator getKeyPairGenerator() {
        if (this.keyPairGenerator != null) {
            return this.keyPairGenerator;
        }
        
        synchronized (this) {
            if (this.keyPairGenerator != null) {
                return this.keyPairGenerator;
            }
            
            this.keyPairGenerator = this.newKeyPairGenerator();
            if (this.keyPairGenerator == null) {
                throw new UnsupportedOperationException();
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
