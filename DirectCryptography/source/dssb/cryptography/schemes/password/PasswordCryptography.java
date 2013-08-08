package dssb.cryptography.schemes.password;

import javax.crypto.SecretKey;

import dssb.cryptography.Cryptography;
import dssb.cryptography.Scheme;

public class PasswordCryptography implements Cryptography, Cryptography.WithCipher {
    
    private final SecretKey secretKey;
    
    private final Scheme scheme;
    
    public PasswordCryptography(
            final SecretKey secretKey) {
        this.scheme = PasswordScheme.INSTANCE;
        this.secretKey = secretKey;
    }
    @Override
    public Scheme getScheme() {
        return this.scheme;
    }
    
    public WithCipher withCipher() {
        return (WithCipher)this;
    }
    
    @Override
    public WithSignature withSignature() {
        return null;
    }
    
    @Override
    public PasswordCipher newCipher() {
        return new PasswordCipher(this, this.secretKey);
    }
    
}
