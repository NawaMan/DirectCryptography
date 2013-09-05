package dssb.cryptography.schemes.aes;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import dssb.cryptography.cipher.DecyptionException;
import dssb.cryptography.cipher.EncyptionException;
import dssb.cryptography.javascrypto.JavaCryptoSecretKeyScheme;

/**
 * Cryptography scheme for AES.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class Aes extends JavaCryptoSecretKeyScheme.Simple<Aes>  {
    
    /** The AES algorithm name. */
    public static final String AES_ALGORITHM_NAME = "AES";
    
    /** Static import instance. */
    public static final Aes Aes = new Aes();
    
    /** Shorthand instance. */
    public static final Aes _ = Aes;
    
    /** Semantic instance. */
    public static final Aes Scheme = Aes;
    
    /** Conventional instance. */
    public static final Aes INSTANCE = Aes;
    
    /** The default key size. */
    public static final KeySize DEFAULT_KEYSIZE = KeySize._256;
    
    /** The key size. */
    public static enum KeySize {
        
        /** 128 bits. */
        _128(128),
        
        /** 192 bits. */
        _192(192),
        
        /** 256 bits. */
        _256(256);
        
        /**
         * Returns the key size enum instance associated with the size in interger.
         * 
         * @param size
         *            the key size in integer.
         * @return the key size enum instance.
         **/
        public static KeySize valueOf(
                final int size) {
            switch (size) {
                case 128:
                    return _128;
                case 192:
                    return _192;
                case 256:
                    return _256;
            }
            return null;
        }
        
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
     * The builder for AES cryptography.
     */
    public static class CryptographyBuilder extends AbstractAesCryptographyBuilder<Aes> {
        /** Constructor. */
        CryptographyBuilder() {
            super(Scheme);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("unchecked")
    public Aes.CryptographyBuilder createCryptographyBuilder() {
        return new Aes.CryptographyBuilder();
    }
    
    @Override
    public byte[] encrypt(
            final SecretKey secretKey,
            final byte[] clearData)
            throws EncyptionException {
        try {
            final byte[] raw = secretKey.getEncoded();
            final SecretKeySpec skeySpec = new SecretKeySpec(raw, AES_ALGORITHM_NAME);
            final javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(AES_ALGORITHM_NAME);
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, skeySpec);
            final byte[] secret = cipher.doFinal(clearData);
            return secret;
        } catch (final NoSuchAlgorithmException problem) {
            throw new EncyptionException();
        } catch (final NoSuchPaddingException problem) {
            throw new EncyptionException(problem);
        } catch (IllegalBlockSizeException problem) {
            throw new EncyptionException(problem);
        } catch (BadPaddingException problem) {
            throw new EncyptionException(problem);
        } catch (InvalidKeyException problem) {
            throw new EncyptionException(problem);
        }
    }
    
    @Override
    public byte[] decrypt(
            SecretKey secretKey,
            byte[] encryptedData)
            throws DecyptionException {
        try {
            final byte[] raw = secretKey.getEncoded();
            final Key k = new SecretKeySpec(raw, AES_ALGORITHM_NAME);
            
            final javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(AES_ALGORITHM_NAME);
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, k);
            
            final byte[] clearBytes = cipher.doFinal(encryptedData);
            return clearBytes;
        } catch (final NoSuchAlgorithmException problem) {
            throw new DecyptionException(problem);
        } catch (final NoSuchPaddingException problem) {
            throw new DecyptionException(problem);
        } catch (final InvalidKeyException problem) {
            throw new DecyptionException(problem);
        } catch (final IllegalBlockSizeException problem) {
            throw new DecyptionException(problem);
        } catch (final BadPaddingException problem) {
            throw new DecyptionException(problem);
        }
    }
    
}
