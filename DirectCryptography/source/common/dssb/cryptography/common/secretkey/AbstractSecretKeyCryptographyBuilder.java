package dssb.cryptography.common.secretkey;

import javax.crypto.SecretKey;

import dssb.cryptography.CryptographyBuilder;

abstract public class AbstractSecretKeyCryptographyBuilder implements CryptographyBuilder {
    
    private SecretKey secretKey;
    
    public SecretKey getSecretKey() {
        return this.secretKey;
    }
    
    protected void setSecretKey(final SecretKey secretKey) {
        this.secretKey = secretKey;
    }
    
}
