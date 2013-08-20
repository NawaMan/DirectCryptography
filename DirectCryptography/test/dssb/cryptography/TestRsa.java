package dssb.cryptography;

import java.util.Arrays;

import org.junit.Test;

import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.cipher.Decryptor;
import dssb.cryptography.cipher.Encryptor;
import dssb.cryptography.datatypes.SerializableType;
import dssb.cryptography.datatypes.TextType;
import dssb.cryptography.schemes.rsa.RsaCryptographyBuilder;
import dssb.cryptography.schemes.rsa.RsaScheme;

public class TestRsa {
    
    @Test
    public void test() throws Exception {
        final RsaCryptographyBuilder cryptBuilder = RsaScheme.INSTANCE.createCryptographyBuilder();
        cryptBuilder.useNewKeyPair();
        
        final Cipher cipher = cryptBuilder.newCryptography().getFeature(Cipher.class);
        final Encryptor encryptor = cipher.getEncryptor();
        final Decryptor decryptor = cipher.getDecryptor();
        
        final SerializableType<Integer> INT = new SerializableType<Integer>(Integer.class);
        
        System.out.println(decryptor.decrypt(encryptor.encrypt(new TextType.Data("Some text here.")), TextType.TYPE));
        System.out.println(decryptor.decrypt(encryptor.encrypt(new Data.Simple<Integer>(INT, 5)), INT).getData());
        
        System.out.println(Arrays.toString("Some text here.".getBytes()));
        System.out.println(Arrays.toString(decryptor.decrypt(encryptor.encrypt("Some text here.".getBytes()))));
    }
    
}
