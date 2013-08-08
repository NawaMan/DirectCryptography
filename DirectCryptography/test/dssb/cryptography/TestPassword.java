package dssb.cryptography;

import java.util.Arrays;

import org.junit.Test;

import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.cipher.Decryptor;
import dssb.cryptography.cipher.Encryptor;
import dssb.cryptography.cipher.EncyptionException;
import dssb.cryptography.datatypes.SerializeType;
import dssb.cryptography.datatypes.Text;
import dssb.cryptography.schemes.password.PasswordCryptographyBuilder;
import dssb.cryptography.schemes.password.PasswordScheme;

public class TestPassword {
    
    @Test
    public void test()
            throws Exception {
        final PasswordCryptographyBuilder cryptBuilder1 = PasswordScheme.INSTANCE.createCryptographyBuilder();
        cryptBuilder1.setPassword("mypassword");
        
        final Cipher cipher1 = cryptBuilder1.newCryptography().withCipher().newCipher();
        final Encryptor encryptor1 = cipher1.getEncryptor();
        final Decryptor decryptor1 = cipher1.getDecryptor();
        
        final SerializeType<Integer> INT = new SerializeType<Integer>(Integer.class);
        
        System.out.println(decryptor1.decrypt(encryptor1.encrypt(new Text("Some text here.")), Text.TYPE));
        System.out.println(decryptor1.decrypt(encryptor1.encrypt(new Data.Simple<Integer>(INT, 5)), INT).getData());
        
        System.out.println(Arrays.toString("Some text here.".getBytes()));
        System.out.println(Arrays.toString(decryptor1.decrypt(encryptor1.encrypt("Some text here.".getBytes()))));
        
        final PasswordCryptographyBuilder cryptBuilder2 = new PasswordCryptographyBuilder();
        cryptBuilder2.setPassword("yourpassword");
        final Cipher cipher2 = cryptBuilder2.newCryptography().withCipher().newCipher();
        final Decryptor decryptor2 = cipher2.getDecryptor();
        
        try {
            System.out.println(decryptor2.decrypt(encryptor1.encrypt(new Text("Some text here.")), Text.TYPE));
        } catch (final EncyptionException problem) {
            problem.printStackTrace();
        }
        
        try {
            System.out.println(decryptor2.decrypt(encryptor1.encrypt(new Data.Simple<Integer>(INT, 5)), INT).getData());
        } catch (final EncyptionException problem) {
            problem.printStackTrace();
        }
        
        try {
            System.out.println(Arrays.toString("Some text here.".getBytes()));
        } catch (final EncyptionException problem) {
            problem.printStackTrace();
        }
        try {
            System.out.println(Arrays.toString(decryptor2.decrypt(encryptor1.encrypt("Some text here.".getBytes()))));
        } catch (final EncyptionException problem) {
            problem.printStackTrace();
        }
    }
    
}
