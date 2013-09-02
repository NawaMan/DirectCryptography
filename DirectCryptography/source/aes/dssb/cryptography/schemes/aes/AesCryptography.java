package dssb.cryptography.schemes.aes;

import javax.crypto.SecretKey;

import dssb.cryptography.common.secretkey.AbstractCommonSecretKeyCryptography;

/**
 * Cryptography for AES.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class AesCryptography extends AbstractCommonSecretKeyCryptography {
    
    /**
     * Constructor.
     * 
     * @param secretKey
     *            the secretkey.
     */
    public AesCryptography(
            final SecretKey secretKey) {
        super(AesScheme.INSTANCE, secretKey);
    }
    
    /** {@inheritDoc} */
    @Override
    protected AesCipher newCipher() {
        final SecretKey secretKey = this.getSecretKey();
        final AesCipher cipher = new AesCipher(this, secretKey);
        return cipher;
    }
    
}
