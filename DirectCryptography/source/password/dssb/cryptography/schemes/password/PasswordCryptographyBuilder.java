package dssb.cryptography.schemes.password;

import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import dssb.cryptography.common.secretkey.SecretKeyCryptographyBuilder;

/**
 * Password-base cryptography builder.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class PasswordCryptographyBuilder extends SecretKeyCryptographyBuilder {
    
    // These constants are here to make CheckStyle happy.
    
    /** Constant for 0x61. */
    private static final int INT_0X61 = 0x61;
    
    /** Constant for 0x64. */
    private static final int INT_0X64 = 0x64;
    
    /** Constant for 0x6F. */
    private static final int INT_0X6F = 0x6F;
    
    /** Constant for 0x70. */
    private static final int INT_0X70 = 0x70;
    
    /** Constant for 0x72. */
    private static final int INT_0X72 = 0x72;
    
    /** Constant for 0x73. */
    private static final int INT_0X73 = 0x73;
    
    /** Constant for 0x77. */
    private static final int INT_0X77 = 0x77;
    
    /** Constant for 256. */
    private static final int INT_256 = 256;
    
    /** Constant for 65536. */
    private static final int INT_65536 = 65536;
    
    /** Salt. */
    private byte[] salt = new byte[] {(byte) INT_0X70, (byte) INT_0X61, (byte) INT_0X73, (byte) INT_0X73,
            (byte) INT_0X77, (byte) INT_0X6F, (byte) INT_0X72, (byte) INT_0X64 };
    
    /**
     * Change the password.
     * 
     * @param password
     *            the password.
     */
    public void setPassword(
            final String password) {
        final char[] pwChars = password.toCharArray();
        try {
            final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            final KeySpec spec = new PBEKeySpec(pwChars, this.salt, INT_65536, INT_256);
            
            final byte[] tmpSecret = factory.generateSecret(spec).getEncoded();
            final SecretKey secretKey = new SecretKeySpec(tmpSecret, "AES");
            this.setSecretKey(secretKey);
        } catch (final Exception problem) {
            // TODO - Do thing about this.
            throw new RuntimeException(problem);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public PasswordCryptography newCryptography() {
        final SecretKey secretKey = this.getSecretKey();
        final PasswordCryptography cryptography = new PasswordCryptography(secretKey);
        return cryptography;
    }
    
}
