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
public abstract class KeyPairCryptographyBuilder implements CryptographyBuilder {
    
    /** The private key. */
    private PrivateKey privateKey = null;
    
    /** The public key. */
    private PublicKey publicKey = null;
    
    /** The key pair generator. */
    private volatile CryptographyKeyPairGenerator keyPairGenerator;
    
    /**
     * Change the key pair.
     * 
     * @param <_Builder_>
     *            the cryptography builder.
     * @param keyPair
     *            the key pair.
     * @return the builder.
     */
    protected <_Builder_ extends KeyPairCryptographyBuilder> _Builder_ setKeyPair(
            final KeyPair keyPair) {
        this.publicKey = (keyPair == null)
                ? null
                : keyPair.getPublic();
        this.privateKey = (keyPair == null)
                ? null
                : keyPair.getPrivate();
        @SuppressWarnings("unchecked")
        final _Builder_ builder = (_Builder_) this;
        return builder;
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
     * @param <_Builder_>
     *            the cryptography builder.
     * @param privateKey
     *            the private key.
     * @return the builder.
     */
    protected <_Builder_ extends KeyPairCryptographyBuilder> _Builder_ setPrivateKey(
            final PrivateKey privateKey) {
        this.privateKey = privateKey;
        @SuppressWarnings("unchecked")
        final _Builder_ builder = (_Builder_) this;
        return builder;
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
     * @param <_Builder_>
     *            the cryptography builder.
     * @param publicKey
     *            the public key.
     * @return the builder.
     */
    protected <_Builder_ extends KeyPairCryptographyBuilder> _Builder_ setPublicKey(
            final PublicKey publicKey) {
        this.publicKey = publicKey;
        @SuppressWarnings("unchecked")
        final _Builder_ builder = (_Builder_) this;
        return builder;
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
            
            this.keyPairGenerator = this.newCryptographyKeyPairGenerator();
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
    protected CryptographyKeyPairGenerator newCryptographyKeyPairGenerator() {
        return null;
    }
    
}
