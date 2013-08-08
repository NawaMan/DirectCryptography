package dssb.cryptography.schemes.sha1rsa;

import dssb.cryptography.Scheme;

public enum Sha1RsaScheme implements Scheme {
    
    INSTANCE;
    
    @Override
    public Sha1RsaCryptographyBuilder createCryptographyBuilder() {
        return new Sha1RsaCryptographyBuilder();
    }
    
}
