package dssb.cryptography.test;

import org.junit.Before;

import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.schemes.password.PasswordCryptographyBuilder;
import dssb.cryptography.schemes.password.PasswordScheme;

/**
 * Test for Password cryptography.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class TestPassword extends AbstractCipherTest {
    
    /** Prepare the encryptor. */
    @Before
    public void init() {
        final PasswordCryptographyBuilder cryptBuilder = PasswordScheme.INSTANCE.createCryptographyBuilder();
        cryptBuilder.setPassword("mypassword");
        
        final Cipher cipher = cryptBuilder.newCryptography().getFeature(Cipher.class);
        encryptor(cipher.getEncryptor());
        decryptor(cipher.getDecryptor());
    }
    
}
