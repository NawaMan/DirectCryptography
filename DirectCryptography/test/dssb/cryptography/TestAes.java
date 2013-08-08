package dssb.cryptography;

import java.util.Arrays;

import org.junit.Test;

import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.cipher.Decryptor;
import dssb.cryptography.cipher.Encryptor;
import dssb.cryptography.datatypes.SerializeType;
import dssb.cryptography.datatypes.Text;
import dssb.cryptography.schemes.aes.AesCryptographyBuilder;
import dssb.cryptography.schemes.aes.AesScheme;

public class TestAes {
    
    @Test
    public void test() throws Exception {
        final AesCryptographyBuilder cryptBuilder = AesScheme.INSTANCE.createCryptographyBuilder();
        cryptBuilder.useNewKey();
        
        final Cipher cipher = cryptBuilder.newCryptography().withCipher().newCipher();
        final Encryptor encryptor = cipher.getEncryptor();
        final Decryptor decryptor = cipher.getDecryptor();
        
        final SerializeType<Integer> INT = new SerializeType<Integer>(Integer.class);
        
        System.out.println(decryptor.decrypt(encryptor.encrypt(new Text("Some text here.")), Text.TYPE));
        System.out.println(decryptor.decrypt(encryptor.encrypt(new Data.Simple<Integer>(INT, 5)), INT).getData());
        
        System.out.println(Arrays.toString("Some text here.".getBytes()));
        System.out.println(Arrays.toString(decryptor.decrypt(encryptor.encrypt("Some text here.".getBytes()))));
    }
    
}
