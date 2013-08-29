package dssb.cryptography.schemes.password;

import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import dssb.cryptography.common.secretkey.AbstractCommonSecretKeyCryptographyBuilder;

public class PasswordCryptographyBuilder extends AbstractCommonSecretKeyCryptographyBuilder {
    
    private byte[] salt = new byte[] { (byte) 0x70, (byte) 0x61, (byte) 0x73, (byte) 0x73, (byte) 0x77, (byte) 0x6f,
        (byte) 0x72, (byte) 0x64 };
    
    public void setPassword(
            final String password) {
        final char[] pwChars = password.toCharArray();
        try {
            final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(pwChars, this.salt, 65536, 256);
            
            final byte[] tmpSecret = factory.generateSecret(spec).getEncoded();
            final SecretKey secretKey = new SecretKeySpec(tmpSecret, "AES");
            this.setSecretKey(secretKey);
            
        } catch (final Exception problem) {
            // TODO - Do thing about this.
            throw new RuntimeException(problem);
        }
    }
    
    @Override
    public PasswordCryptography newCryptography() {
        final SecretKey secretKey = this.getSecretKey();
        final PasswordCryptography cryptography = new PasswordCryptography(secretKey);
        return cryptography;
    }
    
}
