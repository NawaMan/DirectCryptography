package dssb.cryptography.test;

import org.junit.Assert;
import org.junit.Test;

import dssb.cryptography.Data;
import dssb.cryptography.cipher.Decryptor;
import dssb.cryptography.cipher.Encryptor;
import dssb.cryptography.datatypes.SerializableType;
import dssb.cryptography.datatypes.TextType;

/**
 * Common functionality for testing for cipher.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class AbstractCipherTest {
    
    /** The test integer value. */
    private static final int TEST_INT = 5;
    
    /** The encryptor. */
    private Encryptor encryptor;
    /** The decryptor. */
    private Decryptor decryptor;
    
    /**
     * Returns an encryptor.
     * 
     * @return an encryptor.
     **/
    protected Encryptor encryptor() {
        return this.encryptor;
    }
    
    /**
     * Change the encryptor.
     * 
     * @param encryptor
     *            the new encryptor.
     */
    protected void encryptor(
            final Encryptor encryptor) {
        this.encryptor = encryptor;
    }
    
    /**
     * Returns a decryptor.
     * 
     * @return a decryptor.
     **/
    protected Decryptor decryptor() {
        return this.decryptor;
    }
    
    /**
     * Change the decryptor.
     * 
     * @param decryptor
     *            the new decryptor.
     */
    protected void decryptor(
            final Decryptor decryptor) {
        this.decryptor = decryptor;
    }
    
    /** The test for int. **/
    @Test
    public void testInt() {
        final SerializableType<Integer> intType = new SerializableType<Integer>(Integer.class);
        final Data<Integer> startInteger = new Data.Simple<Integer>(intType, 5);
        final Data<Integer> returnInteger = decryptor.decrypt(encryptor.encrypt(startInteger), intType);
        Assert.assertEquals(TEST_INT, (int) returnInteger.getData());
    }
    
    /** The test for TEXT. **/
    @Test
    public void testTextType() {
        final TextType.Data startText = new TextType.Data("Some text here.");
        final TextType.Data returnText = (TextType.Data) decryptor.decrypt(encryptor.encrypt(startText), TextType.TYPE);
        Assert.assertEquals(startText, returnText);
    }
    
    /** The test for string. **/
    @Test
    public void testString() {
        final byte[] clearBytes = "Some text here.".getBytes();
        final byte[] decrypedBytes = decryptor.decrypt(encryptor.encrypt("Some text here.".getBytes()));
        Assert.assertArrayEquals(clearBytes, decrypedBytes);
    }
    
}
