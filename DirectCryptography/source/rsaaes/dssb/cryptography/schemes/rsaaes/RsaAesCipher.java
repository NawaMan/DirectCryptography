package dssb.cryptography.schemes.rsaaes;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import dssb.cryptography.Cryptography;
import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.cipher.EncyptionException;
import dssb.cryptography.datatypes.ArrayOfBytesType;
import dssb.cryptography.schemes.aes.AesCryptographyBuilder;
import dssb.cryptography.schemes.rsa.RsaCipher;

public class RsaAesCipher extends RsaCipher {
    
    public RsaAesCipher(
            final Cryptography cryptography,
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(cryptography, privateKey, publicKey);
    }
    
    @Override
    public byte[] encrypt(
            final byte[] bytes)
            throws EncyptionException {
        final AesCryptographyBuilder aesCryptBuilder = this.newAesCryptBuilder();
        
        final SecretKey dataKey = aesCryptBuilder.getSecretKey();
        final byte[] dataKeyBytes = dataKey.getEncoded();
        final byte[] keyBytes = super.encrypt(dataKeyBytes);
        
        final Cipher cipher = aesCryptBuilder.newCryptography().getFeature(Cipher.class);
        final dssb.cryptography.cipher.Encryptor encryptor = cipher.getEncryptor();
        final byte[] dataBytes = encryptor.encrypt(bytes);
        
        final byte[] secret = ArrayOfBytesType.TYPE.toBytes(keyBytes, dataBytes);
        return secret;
    }
    
    @Override
    public byte[] decrypt(
            final byte[] bytes) {
        final byte[][] allBytes = ArrayOfBytesType.TYPE.fromBytes(bytes);
        final byte[] keyBytes = allBytes[0];
        final byte[] dataBytes = allBytes[1];
        
        final byte[] dataKeyBytes = super.decrypt(keyBytes);
        final SecretKey dataKey = new SecretKeySpec(dataKeyBytes, "AES");
        
        final AesCryptographyBuilder aesCryptBuilder = newAesCryptBuilder();
        aesCryptBuilder.setSecretKey(dataKey);
        final Cipher cipher = aesCryptBuilder.newCryptography().getFeature(Cipher.class);
        final byte[] clearDataBytes = cipher.getDecryptor().decrypt(dataBytes);
        return clearDataBytes;
    }
    
    protected AesCryptographyBuilder newAesCryptBuilder() {
        final AesCryptographyBuilder aesCryptBuilder = new AesCryptographyBuilder();
        try {
            aesCryptBuilder.useNewKey();
        } catch (final NoSuchAlgorithmException problem) {
            throw new EncyptionException(problem);
        } catch (final NoSuchProviderException problem) {
            throw new EncyptionException(problem);
        }
        return aesCryptBuilder;
    }
    
}
