package dssb.cryptography.schemes.aes;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import dssb.cryptography.Cryptography;
import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.cipher.DecyptionException;
import dssb.cryptography.cipher.EncyptionException;

public class AesCipher extends Cipher.Simple {
    
    private SecretKey secretKey;
    
    public AesCipher(
            final Cryptography cryptography,
            final SecretKey secretKey) {
        super(cryptography);
        this.secretKey = secretKey;
    }
    
    @Override
    public byte[] encrypt(
            final byte[] bytes)
            throws EncyptionException {
        try {
            final byte[] raw = AesCipher.this.secretKey.getEncoded();
            final SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            final javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, skeySpec);
            final byte[] secret = cipher.doFinal(bytes);
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
            final byte[] bytes) {
        try {
            final byte[] raw = AesCipher.this.secretKey.getEncoded();
            final Key k = new SecretKeySpec(raw, "AES");
            
            final javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, k);
            
            final byte[] clearBytes = cipher.doFinal(bytes);
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
