package dssb.cryptography.schemes.rsaaes;

import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.schemes.aes.AesScheme;
import dssb.cryptography.schemes.rsa.RsaCryptography;

public class RsaAesCryptography extends RsaCryptography {
    
    private final RsaCryptography rsaCryptography;
    
    public RsaAesCryptography(
            final RsaCryptography rsaCryptography,
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(AesScheme.INSTANCE, privateKey, publicKey);
        this.rsaCryptography = rsaCryptography;
    }
    
    /** {@inheritDoc} */
    @Override
    protected RsaAesCipher newCipher() {
        final PrivateKey privateKey = this.getPrivateKey();
        final PublicKey publicKey = this.getPublicKey();
        final RsaAesCipher rsaAesCipher = new RsaAesCipher(this, privateKey, publicKey);
        return rsaAesCipher;
    }
    
}
