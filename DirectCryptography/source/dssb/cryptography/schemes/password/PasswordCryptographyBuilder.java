package dssb.cryptography.schemes.password;

import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import dssb.cryptography.CryptographyBuilder;

public class PasswordCryptographyBuilder implements CryptographyBuilder {
    
    private SecretKey secretKey;
    
    private byte[] salt = new byte[] { (byte)0x70, (byte)0x61, (byte)0x73, (byte)0x73, (byte)0x77, (byte)0x6f,
        (byte)0x72, (byte)0x64 };
    
    public void setPassword(
            final String password) {
        final char[] pwChars = password.toCharArray();
        try {
            final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(pwChars, this.salt, 65536, 256);
            
            final byte[] tmpSecret = factory.generateSecret(spec).getEncoded();
            this.secretKey = new SecretKeySpec(tmpSecret, "AES");
        } catch (final Exception problem) {
            // TODO - Do thing about this.
            throw new RuntimeException(problem);
        }
    }
    
    public SecretKey getSecretKey() {
        return this.secretKey;
    }
    
    @Override
    public PasswordCryptography newCryptography() {
        return new PasswordCryptography(this.secretKey);
    }
}
