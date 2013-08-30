package dssb.cryptography.common.keypair;

import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.Scheme;
import dssb.cryptography.common.AbstractCommonCryptography;

/**
 * Common implementation for cryptography with keypair.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class AbstractCommonKeyPairCryptography extends AbstractCommonCryptography {
    
    /** The private key. */
    private final PrivateKey privateKey;
    
    /** The public key. */
    private final PublicKey publicKey;
    
    /**
     * Constructor.
     * 
     * @param scheme
     *            the scheme.
     * @param privateKey
     *            the private key.
     * @param publicKey
     *            the public key.
     **/
    protected AbstractCommonKeyPairCryptography(
            final Scheme scheme,
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(scheme);
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
    
    /**
     * Returns the private key.
     * 
     * @return the private key.
     **/
    protected PrivateKey getPrivateKey() {
        return this.privateKey;
    }
    
    /**
     * Returns the public key.
     * 
     * @return the public key.
     **/
    protected PublicKey getPublicKey() {
        return this.publicKey;
    }
    
}
