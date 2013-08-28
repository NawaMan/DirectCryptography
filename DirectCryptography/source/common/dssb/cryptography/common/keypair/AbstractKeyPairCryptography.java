package dssb.cryptography.common.keypair;

import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.Scheme;
import dssb.cryptography.common.AbstractCryptography;

public class AbstractKeyPairCryptography extends AbstractCryptography {
    
    private final PrivateKey privateKey;
    
    private final PublicKey publicKey;
    
    protected AbstractKeyPairCryptography(
            final Scheme scheme,
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(scheme);
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
    
    protected PrivateKey getPrivateKey() {
        return this.privateKey;
    }
    
    protected PublicKey getPublicKey() {
        return this.publicKey;
    }
    
}
