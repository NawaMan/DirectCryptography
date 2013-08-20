package dssb.cryptography.schemes.rsaaes;

import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.schemes.rsa.RsaCryptography;

public class RsaAesCryptography extends RsaCryptography {
    
    private final RsaCryptography rsaCryptography;
    
    private final PublicKey publicKey;
    
    private final PrivateKey privateKey;
    
    public RsaAesCryptography(
            final RsaCryptography rsaCryptography,
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(privateKey, publicKey);
        this.rsaCryptography = rsaCryptography;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
    
    /** {@inheritDoc} */
    @Override
    protected RsaAesCipher newCipher() {
        return new RsaAesCipher(this, this.privateKey, this.publicKey);
    }
    
    
}
