package dssb.cryptography.schemes.rsa;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.Cryptography;
import dssb.cryptography.Scheme;
import dssb.cryptography.schemes.aes.AesScheme;

public class RsaCryptography implements Cryptography, Cryptography.WithCipher {
    
    private final Scheme scheme;
    
    private PrivateKey privateKey = null;
    
    private PublicKey publicKey = null;
    
    public RsaCryptography(
            final KeyPair keyPair) {
        this(keyPair.getPrivate(), keyPair.getPublic());
    }
    
    public RsaCryptography(
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        this.scheme = AesScheme.INSTANCE;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
    @Override
    public Scheme getScheme() {
        return this.scheme;
    }
    
    public WithCipher withCipher() {
        return (WithCipher)this;
    }
    
    @Override
    public WithSignature withSigner() {
        return null;
    }
    
    @Override
    public RsaCipher newCipher() {
        return new RsaCipher(this, this.privateKey, this.publicKey);
    }
    
}
