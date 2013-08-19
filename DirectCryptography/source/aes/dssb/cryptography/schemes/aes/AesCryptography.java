package dssb.cryptography.schemes.aes;

import javax.crypto.SecretKey;

import dssb.cryptography.Cryptography;
import dssb.cryptography.Scheme;

public class AesCryptography implements Cryptography, Cryptography.WithCipher {
    
    private final SecretKey secretKey;
    
    private final Scheme scheme;
    
    public AesCryptography(
            final SecretKey secretKey) {
        this.scheme = AesScheme.INSTANCE;
        this.secretKey = secretKey;
    }
    @Override
    public Scheme getScheme() {
        return this.scheme;
    }
    
    public WithCipher withCipher() {
        return (WithCipher)this;
    }
    
    public boolean hasCipher() {
        return true;
    }
    
    @Override
    public WithSignature withSignature() {
        return null;
    }
    
    public boolean hasSignature() {
        return false;
    }
    
    @Override
    public AesCipher newCipher() {
        return new AesCipher(this, this.secretKey);
    }
    
}
