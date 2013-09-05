package dssb.cryptography.schemes.rsa;

import dssb.cryptography.common.keypair.KeyPairGenerator;
import dssb.cryptography.javascrypto.JavaCryptoKeyPairScheme;

/**
 * The builder for RSA cryptography.
 * 
 * @param <_Scheme_>
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class AbstractRsaCryptographyBuilder<_Scheme_ extends JavaCryptoKeyPairScheme<_Scheme_>> extends
        JavaCryptoKeyPairScheme.Cryptography.Builder.Public<_Scheme_> {
    
    /** The key pair generator. */
    private KeyPairGenerator.JavaCrypto.Public keyPairGenerator = new KeyPairGenerator.JavaCrypto.Public(
            Rsa.RSA_ALGORITHM_NAME, Rsa.DEFAULT_KEYSIZE);
    
    /**
     * Constructor.
     * 
     * @param scheme
     *            the scheme.
     */
    protected AbstractRsaCryptographyBuilder(
            final _Scheme_ scheme) {
        super(scheme);
    }
    
    /** {@inheritDoc} */
    @Override
    protected KeyPairGenerator newKeyPairGenerator() {
        return this.keyPairGenerator;
    }
    
    /**
     * Change the key size.
     * 
     * @param keysize
     *            the key size.
     */
    public void setKeysize(
            final int keysize) {
        this.keyPairGenerator.setKeySize(keysize);
    }
    
}
