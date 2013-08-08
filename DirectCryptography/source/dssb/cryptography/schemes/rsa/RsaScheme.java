package dssb.cryptography.schemes.rsa;

import dssb.cryptography.Scheme;

public enum RsaScheme implements Scheme {
    
    INSTANCE;
    
    @Override
    public RsaCryptographyBuilder createCryptographyBuilder() {
        return new RsaCryptographyBuilder();
    }
    
}
