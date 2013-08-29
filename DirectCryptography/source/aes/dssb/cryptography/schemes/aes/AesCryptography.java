package dssb.cryptography.schemes.aes;

import javax.crypto.SecretKey;

import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.common.secretkey.AbstractCommonSecretKeyCryptography;

public class AesCryptography extends AbstractCommonSecretKeyCryptography {
    
    public AesCryptography(
            final SecretKey secretKey) {
        super(AesScheme.INSTANCE, secretKey);
    }
    
    /**
     * Creates a new cipher.
     * 
     * @return a newly created {@link Cipher}.
     */
    protected AesCipher newCipher() {
        final SecretKey secretKey = this.getSecretKey();
        final AesCipher cipher = new AesCipher(this, secretKey);
        return cipher;
    }
    
}
