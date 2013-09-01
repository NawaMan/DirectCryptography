package dssb.cryptography.test;

import org.junit.Before;

import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.schemes.aes.AesCryptographyBuilder;
import dssb.cryptography.schemes.aes.AesScheme;

/**
 * Test for AES.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class TestAes extends AbstractCipherTest {
    
    /**
     * Prepare the encryptor and decryptor.
     * 
     * @throws Exception
     *             the problem.
     **/
    @Before
    public void init()
            throws Exception {
        final AesCryptographyBuilder cryptBuilder = AesScheme.INSTANCE.createCryptographyBuilder();
        cryptBuilder.useNewKey();
        
        final Cipher cipher = cryptBuilder.newCryptography().getFeature(Cipher.class);
        encryptor(cipher.getEncryptor());
        decryptor(cipher.getDecryptor());
    }
    
}
