package dssb.cryptography.common.secretkey;

import javax.crypto.SecretKey;

import dssb.cryptography.Scheme;
import dssb.cryptography.common.AbstractCryptography;

public class AbstractSecretKeyCryptography extends AbstractCryptography {
    
    private final SecretKey secretKey;
    
    public AbstractSecretKeyCryptography(
            final Scheme scheme,
            final SecretKey secretKey) {
        super(scheme);
        this.secretKey = secretKey;
    }
    
    protected SecretKey getSecretKey() {
        return this.secretKey;
    }
    
}
