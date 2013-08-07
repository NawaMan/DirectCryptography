package dssb.cryptography.schemes.rsaaes;

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

public enum RsaAesScheme implements Scheme {
    
    INSTANCE;
    
    @Override
    public RsaAesCryptographyBuilder createCryptographyBuilder() {
        return new RsaAesCryptographyBuilder();
    }
    
    static public void main(
            final String... args)
            throws NoSuchAlgorithmException,
                NoSuchProviderException {
        final RsaAesCryptographyBuilder cryptBuilder = RsaAesScheme.INSTANCE.createCryptographyBuilder();
        cryptBuilder.useNewKeyPair();
        
        final Cipher cipher = cryptBuilder.newCryptography().withCipher().newCipher();
        final Encryptor encryptor = cipher.getEncryptor();
        final Decryptor decryptor = cipher.getDecryptor();
        
        final SerializeType<Integer> INT = new SerializeType<Integer>(Integer.class);
        
        System.out.println(decryptor.decrypt(encryptor.encrypt(new Text("Some text here.")), Text.TYPE));
        System.out.println(decryptor.decrypt(encryptor.encrypt(new Data.Simple<Integer>(INT, 5)), INT).getData());
        
        System.out.println(Arrays.toString("Some text here.".getBytes()));
        System.out.println(Arrays.toString(decryptor.decrypt(encryptor.encrypt("Some text here.".getBytes()))));
        
        final String longString = "Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here.Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here. Some text here.";
        System.out.println(Arrays.toString(longString.getBytes()));
        System.out.println(Arrays.toString(decryptor.decrypt(encryptor.encrypt(longString.getBytes()))));
    }
    
}
