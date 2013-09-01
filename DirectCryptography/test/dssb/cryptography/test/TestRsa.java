package dssb.cryptography.test;

import org.junit.Before;

import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.schemes.rsa.RsaCryptographyBuilder;
import dssb.cryptography.schemes.rsa.RsaScheme;

/**
 * Test for RSA cryptography.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class TestRsa extends AbstractCipherTest {
    
    /** Prepare the encryptor. */
    @Before
    public void init() {
        final RsaCryptographyBuilder cryptBuilder = RsaScheme.INSTANCE.createCryptographyBuilder();
        cryptBuilder.useNewKeyPair();
        
        final Cipher cipher = cryptBuilder.newCryptography().getFeature(Cipher.class);
        encryptor(cipher.getEncryptor());
        decryptor(cipher.getDecryptor());
    }
    
}
