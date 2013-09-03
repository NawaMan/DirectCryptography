package dssb.cryptography.schemes.rsa;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import dssb.cryptography.cipher.DecyptionException;
import dssb.cryptography.cipher.EncyptionException;
import dssb.cryptography.javascrypto.JavaCryptoKeyPairScheme;

/**
 * RSA cryptography scheme.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class Rsa extends JavaCryptoKeyPairScheme.Simple<Rsa> {
    
    /** The RSA algorithm name. */
    private static final String RSA_ALGORITHM_NAME = "RSA";
    
    /** Static import instance. */
    public static final Rsa Rsa = new Rsa();
    
    /** Shorthand instance. */
    public static final Rsa _ = Rsa;
    
    /** Semantic instance. */
    public static final Rsa Scheme = Rsa;
    
    /** Conventional instance. */
    public static final Rsa INSTANCE = Rsa;
    
    /**
     * The builder for RSA cryptography.
     */
    public static class CryptographyBuilder extends RsaCryptographyBuilder<Rsa> {
        /** Constructor. */
        CryptographyBuilder() {
            super(Rsa);
        }
    }
    
    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public CryptographyBuilder createCryptographyBuilder() {
        final CryptographyBuilder builder = new CryptographyBuilder();
        return builder;
    }
    
    /** {@inheritDoc} */
    @Override
    public byte[] encrypt(
            final PublicKey publicKey,
            final byte[] clearData)
            throws EncyptionException {
        try {
            final javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(RSA_ALGORITHM_NAME);
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, publicKey);
            final byte[] secret = cipher.doFinal(clearData);
            return secret;
        } catch (final NoSuchAlgorithmException problem) {
            throw new EncyptionException(problem);
        } catch (final NoSuchPaddingException problem) {
            throw new EncyptionException(problem);
        } catch (final InvalidKeyException problem) {
            throw new EncyptionException(problem);
        } catch (final IllegalBlockSizeException problem) {
            throw new EncyptionException(problem);
        } catch (final BadPaddingException problem) {
            throw new EncyptionException(problem);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public byte[] decrypt(
            final PrivateKey privateKey,
            final byte[] encryptedData)
            throws DecyptionException {
        try {
            final javax.crypto.Cipher desCipher = javax.crypto.Cipher.getInstance(RSA_ALGORITHM_NAME);
            desCipher.init(javax.crypto.Cipher.DECRYPT_MODE, privateKey);
            final byte[] clearBytes = desCipher.doFinal(encryptedData);
            return clearBytes;
        } catch (final NoSuchAlgorithmException problem) {
            throw new EncyptionException(problem);
        } catch (final NoSuchPaddingException problem) {
            throw new EncyptionException(problem);
        } catch (final InvalidKeyException problem) {
            throw new EncyptionException(problem);
        } catch (final IllegalBlockSizeException problem) {
            throw new EncyptionException(problem);
        } catch (final BadPaddingException problem) {
            throw new EncyptionException(problem);
        }
    }
    
}
