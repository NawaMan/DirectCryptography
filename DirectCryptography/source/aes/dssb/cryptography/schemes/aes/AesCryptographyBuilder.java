package dssb.cryptography.schemes.aes;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import dssb.cryptography.common.secretkey.AbstractCommonSecretKeyCryptographyBuilder;

/**
 * Cryptography builder for AES.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class AesCryptographyBuilder extends AbstractCommonSecretKeyCryptographyBuilder {
    
    /** The key size. */
    public static enum KeySize {
        
        /** 128 bits. */
        _128(128),
        
        /** 192 bits. */
        _192(192),
        
        /** 256 bits. */
        _256(256);
        
        /** The key size. */
        private final int keySize;
        
        /**
         * Constructor.
         * 
         * @param keySize
         *            the key size.
         **/
        private KeySize(
                final int keySize) {
            this.keySize = keySize;
        }
        
        /**
         * Returns the key size in bits.
         * 
         * @return the key size in bits.
         */
        public int getKeySize() {
            return this.keySize;
        }
    }
    
    /**
     * Generate a new secret key.
     * 
     * @return a new secret key.
     * 
     * @throws NoSuchAlgorithmException
     *             when the algorithm does not exist.
     * @throws NoSuchProviderException
     *             when the provider does not exist.
     */
    public static SecretKey generateSecretKey()
            throws NoSuchAlgorithmException,
                NoSuchProviderException {
        final SecretKey secretKey = generateSecretKey(DEFAULT_KEYSIZE.getKeySize());
        return secretKey;
    }
    
    /**
     * Generate a new secret key.
     * 
     * @param keysize
     *            the key size.
     * @return a new secret key.
     * @throws NoSuchAlgorithmException
     *             when the algorithm does not exist.
     */
    public static SecretKey generateSecretKey(
            final int keysize)
            throws NoSuchAlgorithmException {
        final KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(keysize);
        final SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }
    
    /** The default key size. */
    public static final KeySize DEFAULT_KEYSIZE = KeySize._256;
    
    /**
     * Use a newly created key.
     * 
     * @throws NoSuchAlgorithmException
     *             when the algorithm does not exist.
     * @throws NoSuchProviderException
     *             when the provider does not exist.
     */
    public void useNewKey()
            throws NoSuchAlgorithmException,
                NoSuchProviderException {
        this.setSecretKey(generateSecretKey());
    }
    
    /**
     * Use a newly created key.
     * 
     * @param keysize
     *            the key size.
     * @throws NoSuchAlgorithmException
     *             when the algorithm does not exist.
     */
    public void useNewKey(
            final int keysize)
            throws NoSuchAlgorithmException {
        this.setSecretKey(generateSecretKey(keysize));
    }
    
    /**
     * Change the secret key.
     * 
     * @param secretKey
     *            the secret key.
     **/
    public void setSecretKey(
            final SecretKey secretKey) {
        super.setSecretKey(secretKey);
    }
    
    /** {@inheritDoc} */
    @Override
    public AesCryptography newCryptography() {
        final SecretKey secretKey = this.getSecretKey();
        final AesCryptography cryptography = new AesCryptography(secretKey);
        return cryptography;
    }
}
