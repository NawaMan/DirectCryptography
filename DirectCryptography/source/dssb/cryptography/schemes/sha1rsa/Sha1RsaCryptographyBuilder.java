package dssb.cryptography.schemes.sha1rsa;

import dssb.cryptography.schemes.rsa.RsaCryptography;
import dssb.cryptography.schemes.rsa.RsaCryptographyBuilder;

public class Sha1RsaCryptographyBuilder extends RsaCryptographyBuilder {
    
    @Override
    public Sha1RsaCryptography newCryptography() {
        final RsaCryptography rsaCryptography = super.newCryptography();
        return new Sha1RsaCryptography(rsaCryptography, this.getPrivateKey(), this.getPublicKey());
    }

}
