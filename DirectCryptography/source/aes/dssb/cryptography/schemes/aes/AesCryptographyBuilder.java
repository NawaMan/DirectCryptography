package dssb.cryptography.schemes.aes;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import dssb.cryptography.CryptographyBuilder;

public class AesCryptographyBuilder implements CryptographyBuilder {
    
    static public enum KeySize {
        
        _128(128), _192(192), _256(256);
        
        private final int keySize;
        
        private KeySize(final int keySize) {
            this.keySize = keySize;
        }
        
        public int getKeySize() {
            return this.keySize;
        }
    }
    
    public static SecretKey generateSecretKey()
            throws NoSuchAlgorithmException,
                NoSuchProviderException {
        final SecretKey secretKey = generateSecretKey(DEFAULT_KEYSIZE.getKeySize());
        return secretKey;
    }
    
    public static SecretKey generateSecretKey(
            final int keysize) throws NoSuchAlgorithmException {
        final KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(keysize);
        final SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }
    
    
    static public final KeySize DEFAULT_KEYSIZE = KeySize._256;
    
    private SecretKey secretKey;
    
    public void setSecretKey(final SecretKey secretKey) {
        this.secretKey = secretKey;
    }
    
    public void useNewKey() throws NoSuchAlgorithmException, NoSuchProviderException{
        this.setSecretKey(generateSecretKey());
    }
    
    public void useNewKey(final int keysize) throws NoSuchAlgorithmException{
        this.setSecretKey(generateSecretKey(keysize));
    }
    
    public SecretKey getSecretKey() {
        return this.secretKey;
    }

    @Override
    public AesCryptography newCryptography() {
        return new AesCryptography(this.secretKey);
    }
}
