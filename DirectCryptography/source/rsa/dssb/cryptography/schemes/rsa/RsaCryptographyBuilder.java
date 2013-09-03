package dssb.cryptography.schemes.rsa;

import dssb.cryptography.common.keypair.KeyPairGenerator;
import dssb.cryptography.javascrypto.JavaCryptoKeyPairScheme;
import dssb.cryptography.javascrypto.JavaCryptoKeyPairScheme.Cryptography;

/**
 * The builder for RSA cryptography.
 * 
 * @param <_Scheme_>
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class RsaCryptographyBuilder<_Scheme_ extends JavaCryptoKeyPairScheme<_Scheme_>> extends
        Cryptography.Builder.Public<_Scheme_> {
    
    /**
     * Constructor.
     * 
     * @param scheme
     *            the scheme.
     */
    public RsaCryptographyBuilder(
            final _Scheme_ scheme) {
        super(scheme);
    }
    
    @Override
    protected KeyPairGenerator newKeyPairGenerator() {
        // TODO - These parameter should be settable.
        final String algorithm = "RSA";
        final int keysize = 1024;
        final KeyPairGenerator generator = new KeyPairGenerator.JavaCrypto(algorithm, keysize);
        return generator;
    }
}
