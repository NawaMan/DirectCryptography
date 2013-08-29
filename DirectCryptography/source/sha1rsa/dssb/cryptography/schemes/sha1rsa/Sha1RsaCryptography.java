package dssb.cryptography.schemes.sha1rsa;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dssb.cryptography.schemes.rsa.RsaCryptography;
import dssb.cryptography.signature.Signature;

public class Sha1RsaCryptography extends RsaCryptography {
    
    private final RsaCryptography rsaCryptography;
    
    public Sha1RsaCryptography(
            final RsaCryptography rsaCryptography,
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(privateKey, publicKey);
        this.rsaCryptography = rsaCryptography;
    }
    
    /**
     * Creates a new cipher.
     * 
     * @return a newly created {@link Signature}.
     */
    protected Sha1RsaSignature newSignature() {
        final PrivateKey privateKey = this.getPrivateKey();
        final PublicKey publicKey = this.getPublicKey();
        final Sha1RsaSignature signature = new Sha1RsaSignature(this, privateKey, publicKey);
        return signature;
    }
    
}
