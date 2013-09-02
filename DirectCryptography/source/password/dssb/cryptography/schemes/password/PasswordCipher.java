package dssb.cryptography.schemes.password;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.cipher.EncyptionException;
import dssb.cryptography.datatypes.ArrayOfBytesType;

/**
 * Cipher for password cryptography.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class PasswordCipher extends Cipher.Simple {
    
    /** The secret key. */
    private SecretKey secretKey;
    
    /**
     * Constructor.
     * 
     * @param passwordCryptography
     *            the password cryptography.
     * @param secretKey
     *            the secret key.
     **/
    public PasswordCipher(
            final PasswordCryptography passwordCryptography,
            final SecretKey secretKey) {
        super(passwordCryptography);
        this.secretKey = secretKey;
    }
    
    /** {@inheritDoc} */
    @Override
    public byte[] encrypt(
            final byte[] bytes)
            throws EncyptionException {
        try {
            final javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, this.secretKey);
            
            final AlgorithmParameters params = cipher.getParameters();
            final byte[] ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
            final byte[] dataBytes = cipher.doFinal(bytes);
            final byte[] secret = ArrayOfBytesType.TYPE.toBytes(ivBytes, dataBytes);
            return secret;
        } catch (final NoSuchAlgorithmException problem) {
            throw new EncyptionException(problem);
        } catch (final NoSuchPaddingException problem) {
            throw new EncyptionException(problem);
        } catch (final InvalidKeyException problem) {
            throw new EncyptionException(problem);
        } catch (final InvalidParameterSpecException problem) {
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
        final byte[][] secret = ArrayOfBytesType.TYPE.fromBytes(bytes);
        final byte[] ivBytes = secret[0];
        final byte[] dataBytes = secret[1];
        
        try {
            final javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5Padding");
            final IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, this.secretKey, ivSpec);
            final byte[] decryptedBytes = cipher.doFinal(dataBytes);
            return decryptedBytes;
        } catch (final NoSuchAlgorithmException problem) {
            throw new EncyptionException(problem);
        } catch (final NoSuchPaddingException problem) {
            throw new EncyptionException(problem);
        } catch (final InvalidKeyException problem) {
            throw new EncyptionException(problem);
        } catch (final InvalidAlgorithmParameterException problem) {
            throw new EncyptionException(problem);
        } catch (final IllegalBlockSizeException problem) {
            throw new EncyptionException(problem);
        } catch (final BadPaddingException problem) {
            throw new EncyptionException(problem);
        }
    }
    
}
