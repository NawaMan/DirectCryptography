package dssb.cryptography.schemes.rsaaes;

import dssb.cryptography.schemes.rsa.RsaCryptography;
import dssb.cryptography.schemes.rsa.RsaCryptographyBuilder;

public class RsaAesCryptographyBuilder extends RsaCryptographyBuilder {
    
    @Override
    public RsaAesCryptography newCryptography() {
        final RsaCryptography rsaCryptography = super.newCryptography();
        return new RsaAesCryptography(rsaCryptography, this.getPrivateKey(), this.getPublicKey());
    }
    
}
