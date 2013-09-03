package dssb.cryptography.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.schemes.rsaaes.RsaAes;


/**
 * Test for RSA+AES cryptography.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class TestRsaAes extends AbstractCipherTest {
    
    /** Prepare the encryptor. */
    @Before
    public void init() {
        final RsaAes.CryptographyBuilder cryptBuilder = RsaAes.Scheme.createCryptographyBuilder();
        cryptBuilder.useNewKeyPair();
        
        final Cipher cipher = cryptBuilder.newCryptography().getFeature(Cipher.class);
        encryptor(cipher.getEncryptor());
        decryptor(cipher.getDecryptor());
        
    }
    
    /** The test for string. **/
    @Test
    public void testLongString() {
        final String longString = "Some text here. Some text here. Some text here. Some text here. Some text here. "
                + "Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. "
                + "Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. "
                + "Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. "
                + "Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. "
                + "Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. "
                + "Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. "
                + "Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. "
                + "Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. "
                + "Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. "
                + "Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. ";
        final byte[] clearBytes = longString.getBytes();
        final byte[] decrypedBytes = decryptor().decrypt(encryptor().encrypt(longString.getBytes()));
        Assert.assertArrayEquals(clearBytes, decrypedBytes);
    }
    
}
