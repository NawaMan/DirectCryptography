package dssb.cryptography;

import java.util.Arrays;

import org.junit.Test;

import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.cipher.Decryptor;
import dssb.cryptography.cipher.Encryptor;
import dssb.cryptography.datatypes.SerializableType;
import dssb.cryptography.datatypes.TextType;
import dssb.cryptography.schemes.rsaaes.RsaAesCryptographyBuilder;
import dssb.cryptography.schemes.rsaaes.RsaAesScheme;

public class TestRsaAes {
    
    @Test
    public void test()
            throws Exception {
        final RsaAesCryptographyBuilder cryptBuilder = RsaAesScheme.INSTANCE.createCryptographyBuilder();
        cryptBuilder.useNewKeyPair();
        
        final Cipher cipher = cryptBuilder.newCryptography().withCipher().newCipher();
        final Encryptor encryptor = cipher.getEncryptor();
        final Decryptor decryptor = cipher.getDecryptor();
        
        final SerializableType<Integer> INT = new SerializableType<Integer>(Integer.class);
        
        System.out.println(decryptor.decrypt(encryptor.encrypt(new TextType.Data("Some text here.")), TextType.TYPE));
        System.out.println(decryptor.decrypt(encryptor.encrypt(new Data.Simple<Integer>(INT, 5)), INT).getData());
        
        System.out.println(Arrays.toString("Some text here.".getBytes()));
        System.out.println(Arrays.toString(decryptor.decrypt(encryptor.encrypt("Some text here.".getBytes()))));
        
        final String longString = "Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here.Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here.";
        System.out.println(Arrays.toString(longString.getBytes()));
        System.out.println(Arrays.toString(decryptor.decrypt(encryptor.encrypt(longString.getBytes()))));
    }
    
}
