package dssb.cryptography.schemes.sha1rsa;

import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.schemes.rsa.RsaCryptography;
import dssb.cryptography.schemes.rsa.RsaCryptographyBuilder;

/**
 * Cryptography for digital signature with SHA1+RSA.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class Sha1RsaCryptography extends RsaCryptography {
    
    /**
     * @param rsaCryptographyBuilder the 
     */
    public Sha1RsaCryptography(
            final RsaCryptographyBuilder rsaCryptographyBuilder) {
        super(Sha1RsaScheme.INSTANCE, rsaCryptographyBuilder.getPrivateKey(), rsaCryptographyBuilder.getPublicKey());
    }
    
    /** {@inheritDoc} */
    @Override
    protected Sha1RsaSignature newSignature() {
        final PrivateKey privateKey = this.getPrivateKey();
        final PublicKey publicKey = this.getPublicKey();
        final Sha1RsaSignature signature = new Sha1RsaSignature(this, privateKey, publicKey);
        return signature;
    }
    
}
