package dssb.cryptography.schemes.rsaaes;

import dssb.cryptography.schemes.rsa.RsaCryptographyBuilder;

/**
 * Cryptography builder for RSA+AES.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class RsaAesCryptographyBuilder extends RsaCryptographyBuilder {
    
    /** {@inheritDoc} */
    @Override
    public RsaAesCryptography newCryptography() {
        return new RsaAesCryptography(this.getPrivateKey(), this.getPublicKey());
    }
    
}
