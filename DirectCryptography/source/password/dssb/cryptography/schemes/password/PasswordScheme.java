package dssb.cryptography.schemes.password;

import dssb.cryptography.Scheme;

public enum PasswordScheme implements Scheme {
    
    INSTANCE;
    
    @Override
    public PasswordCryptographyBuilder createCryptographyBuilder() {
        return new PasswordCryptographyBuilder();
    }
    
}
