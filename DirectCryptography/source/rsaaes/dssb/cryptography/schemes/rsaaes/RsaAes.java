package dssb.cryptography.schemes.rsaaes;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import dssb.cryptography.cipher.DecyptionException;
import dssb.cryptography.cipher.EncyptionException;
import dssb.cryptography.datatypes.ArrayOfBytesType;
import dssb.cryptography.javascrypto.JavaCryptoKeyPairScheme;
import dssb.cryptography.schemes.aes.Aes;
import dssb.cryptography.schemes.rsa.Rsa;
import dssb.cryptography.schemes.rsa.AbstractRsaCryptographyBuilder;

/**
 * RSA+AES cryptography scheme.
 * 
 * <ol>
 * <li>An AES key is created.</li>
 * <li>The key is used to encrypt the data.</li>
 * <li>The key is then encrypted using RSA public key.</li>
 * <li>Both encrypted data are then combined.</li>
 * </ol>
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class RsaAes extends JavaCryptoKeyPairScheme.Simple<RsaAes> {
    
    /** The name of AES algorithm. */
    private static final String AES_ALGORITHM_NAME = "AES";
    
    /** Static import instance. */
    public static final RsaAes RsaAes = new RsaAes();
    
    /** Shorthand instance. */
    public static final RsaAes _ = RsaAes;
    
    /** Semantic instance. */
    public static final RsaAes Scheme = RsaAes;
    
    /** Conventional instance. */
    public static final RsaAes INSTANCE = RsaAes;
    
    /** The Cipher class. */
    private static final Class<dssb.cryptography.cipher.Cipher> CIPHER_CLASS = dssb.cryptography.cipher.Cipher.class;
    
    /**
     * The builder for RSA cryptography.
     */
    public static class CryptographyBuilder extends AbstractRsaCryptographyBuilder<RsaAes> {
        /** Constructor. */
        CryptographyBuilder() {
            super(RsaAes);
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
        
        final Aes.CryptographyBuilder aesCryptBuilder = Aes.Scheme.createCryptographyBuilder();
        aesCryptBuilder.useNewKey();
        
        final SecretKey dataKey = aesCryptBuilder.getSecretKey();
        final byte[] dataKeyBytes = dataKey.getEncoded();
        
        final dssb.cryptography.cipher.Cipher rsaCipher = this.getRsaCipher(null, publicKey);
        final dssb.cryptography.cipher.Encryptor rsaEncryptor = rsaCipher.getEncryptor();
        final byte[] keyBytes = rsaEncryptor.encrypt(dataKeyBytes);
        
        final dssb.cryptography.cipher.Cipher aesCipher = aesCryptBuilder.newCryptography().getFeature(CIPHER_CLASS);
        final dssb.cryptography.cipher.Encryptor aesEncryptor = aesCipher.getEncryptor();
        final byte[] dataBytes = aesEncryptor.encrypt(clearData);
        
        final byte[] secret = ArrayOfBytesType.TYPE.toBytes(keyBytes, dataBytes);
        return secret;
    }
    
    /** {@inheritDoc} */
    @Override
    public byte[] decrypt(
            final PrivateKey privateKey,
            final byte[] encryptedData)
            throws DecyptionException {
        final byte[][] allBytes = ArrayOfBytesType.TYPE.fromBytes(encryptedData);
        final byte[] keyBytes = allBytes[0];
        final byte[] dataBytes = allBytes[1];
        
        final dssb.cryptography.cipher.Cipher rsaCipher = this.getRsaCipher(privateKey, null);
        final byte[] dataKeyBytes = rsaCipher.getDecryptor().decrypt(keyBytes);
        final SecretKey dataKey = new SecretKeySpec(dataKeyBytes, AES_ALGORITHM_NAME);
        
        final Aes.CryptographyBuilder aesCryptBuilder = Aes.Scheme.createCryptographyBuilder();
        aesCryptBuilder.setSecretKey(dataKey);
        
        final dssb.cryptography.Cryptography cryptography = aesCryptBuilder.newCryptography();
        final dssb.cryptography.cipher.Cipher aesCipher = cryptography.getFeature(CIPHER_CLASS);
        final dssb.cryptography.cipher.Decryptor aesDecryptor = aesCipher.getDecryptor();
        final byte[] clearDataBytes = aesDecryptor.decrypt(dataBytes);
        return clearDataBytes;
    }
    
    /**
     * Creates and returns a RSA cipher.
     * 
     * @param privateKey
     *            the private key.
     * @param publicKey
     *            the public key.
     * @return the RSA cipher.
     */
    private final dssb.cryptography.cipher.Cipher getRsaCipher(
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        final Rsa.CryptographyBuilder rsaCryptBuilder = Rsa.Scheme.createCryptographyBuilder();
        rsaCryptBuilder.setPublicKey(publicKey);
        rsaCryptBuilder.setPrivateKey(privateKey);
        final Rsa.Cryptography<Rsa> rsaCryptography = rsaCryptBuilder.newCryptography();
        final dssb.cryptography.cipher.Cipher cipher = rsaCryptography.getFeature(CIPHER_CLASS);
        return cipher;
    }
    
}
