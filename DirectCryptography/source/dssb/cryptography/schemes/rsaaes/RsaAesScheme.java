package dssb.cryptography.schemes.rsaaes;

import dssb.cryptography.Scheme;

public enum RsaAesScheme implements Scheme {
    
    INSTANCE;
    
    @Override
    public RsaAesCryptographyBuilder createCryptographyBuilder() {
        return new RsaAesCryptographyBuilder();
    }
    
}
