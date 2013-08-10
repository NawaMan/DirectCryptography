package dssb.cryptography.schemes.aes;

import dssb.cryptography.Scheme;

public enum AesScheme implements Scheme {
    
    INSTANCE;
    
    @Override
    public AesCryptographyBuilder createCryptographyBuilder() {
        return new AesCryptographyBuilder();
    }
    
}
