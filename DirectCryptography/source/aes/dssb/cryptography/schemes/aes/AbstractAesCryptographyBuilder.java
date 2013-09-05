package dssb.cryptography.schemes.aes;

import dssb.cryptography.common.secretkey.SecretKeyGenerator;
import dssb.cryptography.javascrypto.JavaCryptoSecretKeyGenerator;
import dssb.cryptography.javascrypto.JavaCryptoSecretKeyScheme;
import dssb.cryptography.schemes.aes.Aes.KeySize;

/**
 * Cryptography builder for AES.
 * 
 * @param <_Scheme_>
 *            the scheme.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class AbstractAesCryptographyBuilder<_Scheme_ extends Aes> extends
        JavaCryptoSecretKeyScheme.Cryptography.Builder.Public<Aes> {
    
    /** The key generator. */
    private JavaCryptoSecretKeyGenerator.Public keyGenerator = new JavaCryptoSecretKeyGenerator.Public(
            Aes.AES_ALGORITHM_NAME, Aes.DEFAULT_KEYSIZE.getKeySize());
    
    /**
     * Constructor.
     * 
     * @param scheme
     *            the scheme.
     **/
    protected AbstractAesCryptographyBuilder(
            final _Scheme_ scheme) {
        super(scheme);
    }
    
    /** {@inheritDoc} */
    @Override
    protected SecretKeyGenerator newKeyGenerator() {
        return this.keyGenerator;
    }
    
    /**
     * Change the key size.
     * 
     * @param keysize
     *            the key size.
     */
    public void setKeysize(
            final int keysize) {
        final int validKeySize = KeySize.valueOf(keysize).getKeySize();
        this.keyGenerator.setKeysize(validKeySize);
    }
    
    /**
     * Change the key size.
     * 
     * @param keysize
     *            the key size.
     */
    public void setKeysize(
            final KeySize keysize) {
        final int size = keysize.getKeySize();
        this.keyGenerator.setKeysize(size);
    }
    
}
