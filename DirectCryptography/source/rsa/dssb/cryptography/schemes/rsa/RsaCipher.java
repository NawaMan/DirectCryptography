package dssb.cryptography.schemes.rsa;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.cipher.EncyptionException;

/**
 * @author dssb
 *
 */
public class RsaCipher extends Cipher.Simple {
    
    /** The private key. */
    private final PrivateKey privateKey;
    
    /** The public key. */
    private final PublicKey publicKey;
    
    /**
     * Constructor.
     * 
     * @param rsaCryptography
     *            the RSA cryptography.
     * @param privateKey
     *            the private key.
     * @param publicKey
     *            the public key.
     */
    public RsaCipher(
            final RsaCryptography rsaCryptography,
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(rsaCryptography);
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
    
    /** {@inheritDoc} */
    @Override
    public Encryptor newEncryptor() {
        if (this.publicKey == null) {
            return null;
        } else {
            return super.newEncryptor();
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public Decryptor newDecryptor() {
        if (this.privateKey == null) {
            return null;
        } else {
            return super.newDecryptor();
        }
    }
    
    // TODO - bytes limits.
    /** {@inheritDoc} */
    @Override
    public byte[] encrypt(
            final byte[] bytes)
            throws EncyptionException {
        try {
            final PublicKey publicKey = RsaCipher.this.publicKey;
            final javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA");
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, publicKey);
            final byte[] secret = cipher.doFinal(bytes);
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
            final byte[] bytes) {
        try {
            final PrivateKey privateKey = RsaCipher.this.privateKey;
            final javax.crypto.Cipher desCipher = javax.crypto.Cipher.getInstance("RSA");
            desCipher.init(javax.crypto.Cipher.DECRYPT_MODE, privateKey);
            final byte[] clearBytes = desCipher.doFinal(bytes);
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
