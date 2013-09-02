package dssb.cryptography.schemes.password;

import javax.crypto.SecretKey;

import dssb.cryptography.common.secretkey.AbstractCommonSecretKeyCryptography;

/**
 * Password-base cryptography.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class PasswordCryptography extends AbstractCommonSecretKeyCryptography {
    
    /**
     * Constructor.
     * 
     * @param secretKey
     *            the secret key.
     */
    public PasswordCryptography(
            final SecretKey secretKey) {
        super(PasswordScheme.INSTANCE, secretKey);
    }
    
    /** {@inheritDoc} */
    @Override
    protected PasswordCipher newCipher() {
        final SecretKey secretKey = this.getSecretKey();
        final PasswordCipher cryptography = new PasswordCipher(this, secretKey);
        return cryptography;
    }
    
}
