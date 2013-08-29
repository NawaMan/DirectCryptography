package dssb.cryptography.schemes.rsa;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.Scheme;
import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.common.keypair.AbstractCommonKeyPairCryptography;

public class RsaCryptography extends AbstractCommonKeyPairCryptography {
    
    public RsaCryptography(
            final KeyPair keyPair) {
        this(keyPair.getPrivate(), keyPair.getPublic());
    }
    
    public RsaCryptography(
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(RsaScheme.INSTANCE, privateKey, publicKey);
    }
    
    protected RsaCryptography(
            final Scheme scheme,
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(scheme, privateKey, publicKey);
    }
    
    /**
     * Creates a new cipher.
     * 
     * @return a newly created {@link Cipher}.
     */
    protected RsaCipher newCipher() {
        final PrivateKey privateKey = this.getPrivateKey();
        final PublicKey publicKey = this.getPublicKey();
        final RsaCipher cipher = new RsaCipher(this, privateKey, publicKey);
        return cipher;
    }
    
}
