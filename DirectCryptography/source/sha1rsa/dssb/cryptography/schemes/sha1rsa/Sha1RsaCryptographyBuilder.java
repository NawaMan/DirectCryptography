package dssb.cryptography.schemes.sha1rsa;

import dssb.cryptography.schemes.rsa.RsaCryptographyBuilder;

/**
 * Cryptography builder for SHA1 RSA.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class Sha1RsaCryptographyBuilder extends RsaCryptographyBuilder {
    
    /** {@inheritDoc} */
    @Override
    public Sha1RsaCryptography newCryptography() {
        return new Sha1RsaCryptography(this);
    }
    
}
