package dssb.cryptography.schemes.rsa;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;

import dssb.cryptography.Data;
import dssb.cryptography.Scheme;
import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.cipher.Decryptor;
import dssb.cryptography.cipher.Encryptor;
import dssb.cryptography.datatypes.SerializeType;
import dssb.cryptography.datatypes.Text;

public enum RsaScheme implements Scheme {
    
    INSTANCE;
    
    @Override
    public RsaCryptographyBuilder createCryptographyBuilder() {
        return new RsaCryptographyBuilder();
    }
    
    static public void main(final String ... args) throws NoSuchAlgorithmException, NoSuchProviderException {
        final RsaCryptographyBuilder cryptBuilder = RsaScheme.INSTANCE.createCryptographyBuilder();
        cryptBuilder.useNewKeyPair();
        
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
