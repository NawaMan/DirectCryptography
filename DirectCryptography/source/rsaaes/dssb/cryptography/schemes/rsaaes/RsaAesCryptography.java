package dssb.cryptography.schemes.rsaaes;

import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.schemes.aes.AesScheme;
import dssb.cryptography.schemes.rsa.RsaCryptography;

/**
 * Cryptography for RSA+AES.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class RsaAesCryptography extends RsaCryptography {
    
    /**
     * Constructor.
     * 
     * @param privateKey
     *            the private key.
     * @param publicKey
     *            the public key.
     */
    public RsaAesCryptography(
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(AesScheme.INSTANCE, privateKey, publicKey);
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
