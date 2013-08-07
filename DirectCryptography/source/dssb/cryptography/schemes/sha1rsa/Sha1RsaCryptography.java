package dssb.cryptography.schemes.sha1rsa;

import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.Cryptography.WithSignature;
import dssb.cryptography.schemes.rsa.RsaCryptography;

public class Sha1RsaCryptography extends RsaCryptography implements WithSignature {
        
    private final RsaCryptography rsaCryptography;
    
    private final PublicKey publicKey;
    
    private final PrivateKey privateKey;
    
    public Sha1RsaCryptography(
            final RsaCryptography rsaCryptography,
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(privateKey, publicKey);
        this.rsaCryptography = rsaCryptography;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
    
    @Override
    public WithSignature withSigner() {
        return (WithSignature)this;
    }
    
    @Override
    public Sha1RsaSignature newSignature() {
        final PrivateKey privateKey = this.privateKey;
        final PublicKey publicKey = this.publicKey;
        final Sha1RsaSignature signature = new Sha1RsaSignature(this, privateKey, publicKey);
        return signature;
    }
    
}
