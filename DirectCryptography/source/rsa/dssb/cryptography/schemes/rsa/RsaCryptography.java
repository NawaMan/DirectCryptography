package dssb.cryptography.schemes.rsa;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.Scheme;
import dssb.cryptography.common.keypair.AbstractCommonKeyPairCryptography;

/**
 * Cryptography for RSA.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class RsaCryptography extends AbstractCommonKeyPairCryptography {
    
    /**
     * Constructor.
     * 
     * @param keyPair
     *            the key pair to be used.
     */
    public RsaCryptography(
            final KeyPair keyPair) {
        this(keyPair.getPrivate(), keyPair.getPublic());
    }
    
    /**
     * Constructor.
     * 
     * @param privateKey
     *            the private key.
     * @param publicKey
     *            the public key.
     */
    public RsaCryptography(
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(RsaScheme.INSTANCE, privateKey, publicKey);
    }
    
    /**
     * Constructor.
     * 
     * @param scheme
     *            the cryptography scheme.
     * @param privateKey
     *            the private key.
     * @param publicKey
     *            the public key.
     */
    protected RsaCryptography(
            final Scheme scheme,
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(scheme, privateKey, publicKey);
    }
    
    /**
     * Creates a new cipher.
     * 
     * @return a newly created cipher.
     */
    protected RsaCipher newCipher() {
        final PrivateKey privateKey = this.getPrivateKey();
        final PublicKey publicKey = this.getPublicKey();
        final RsaCipher cipher = new RsaCipher(this, privateKey, publicKey);
        return cipher;
    }
    
}
