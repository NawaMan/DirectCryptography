package dssb.cryptography.schemes.rsa;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.common.keypair.AbstractCommonKeyPairCryptographyBuilder;
import dssb.cryptography.common.keypair.CryptographyKeyPairGenerator;
import dssb.cryptography.common.keypair.KeyPairGenerator;

/**
 * Cryptography builder for RSA.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class RsaCryptographyBuilder extends AbstractCommonKeyPairCryptographyBuilder {
    
    /** The default value for key size. */
    public static final int DEFAULT_KEYSIZE = 1024;
    /** The default value for algorithm name. */
    public static final String DEFAULT_ALGORITHM = "RSA";
    
    @Override
    public RsaCryptography newCryptography() {
        final PrivateKey privateKey = this.getPrivateKey();
        final PublicKey publicKey = this.getPublicKey();
        final RsaCryptography cryptography = new RsaCryptography(privateKey, publicKey);
        return cryptography;
    }
    
    /** {@inheritDoc} */
    @Override
    public void setKeyPair(
            final KeyPair keyPair) {
        super.setKeyPair(keyPair);
    }
    
    /** {@inheritDoc} */
    @Override
    protected void setPrivateKey(
            final PrivateKey privateKey) {
        super.setPrivateKey(privateKey);
    }
    
    /** {@inheritDoc} */
    @Override
    protected void setPublicKey(
            final PublicKey publicKey) {
        super.setPublicKey(publicKey);
    }
    
    /** {@inheritDoc} */
    @Override
    public void useNewKeyPair() {
        super.useNewKeyPair();
    }
    
    /** {@inheritDoc} */
    @Override
    public void useNewKeyPair(
            final KeyPairGenerator generator) {
        super.useNewKeyPair(generator);
    }
    
    /**
     * Generate and use a new keypair.
     * 
     * @param algorithm
     *            the algorithm name.
     * @param keysize
     *            the key size.
     */
    public void useNewKeyPair(
            final String algorithm,
            final int keysize) {
        final KeyPairGenerator generator = new KeyPairGenerator.JavaCrypto(algorithm, keysize);
        this.useNewKeyPair(generator);
    }
    
    /**
     * Returns the default key size.
     * 
     * @return the default key size.
     **/
    protected int getDefaultKeySize() {
        return DEFAULT_KEYSIZE;
    }
    
    /**
     * Returns the default algorithm.
     * 
     * @return the default algorithm.
     **/
    protected String getDefaultAlgorithm() {
        return DEFAULT_ALGORITHM;
    }
    
    /** {@inheritDoc} */
    @Override
    protected CryptographyKeyPairGenerator newKeyPairGenerator() {
        final String algorithm = this.getDefaultAlgorithm();
        final int keysize = this.getDefaultKeySize();
        final KeyPairGenerator generator = new KeyPairGenerator.JavaCrypto(algorithm, keysize);
        return new CryptographyKeyPairGenerator(this, generator) {
        };
    }
}
