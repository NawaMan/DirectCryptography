package dssb.cryptography.schemes.rsa;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import dssb.cryptography.Cryptography;
import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.cipher.EncyptionException;

public class RsaCipher extends Cipher.Simple {
    
    private final PrivateKey privateKey;
    
    private final PublicKey publicKey;
    
    public RsaCipher(
            final Cryptography cryptography,
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(cryptography);
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
    
    @Override
    public Encryptor newEncryptor() {
        if (this.publicKey == null) {
            return null;
        } else {
            return new Encryptor(this);
        }
    }
    
    @Override
    public Decryptor newDecryptor() {
        if (this.privateKey == null) {
            return null;
        } else {
            return new Decryptor(this);
        }
    }
    // TODOD - bytes limits.
    @Override
    public byte[] encrypt(
            final byte[] bytes) throws EncyptionException {
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
