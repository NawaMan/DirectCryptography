package dssb.cryptography.schemes.rsa;

import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.schemes.keypair.KeyPairCryptographyBuilder;

public class RsaCryptographyBuilder extends KeyPairCryptographyBuilder {
    
    public static final int DEFAULT_KEYSIZE = 1024;
    public static final String DEFAULT_ALGORITHM = "RSA";
    
    @Override
    public RsaCryptography newCryptography() {
        final PrivateKey privateKey = this.getPrivateKey();
        final PublicKey publicKey = this.getPublicKey();
        final RsaCryptography cryptography = new RsaCryptography(privateKey, publicKey);
        return cryptography;
    }
    
    @Override
    protected int getDefaultKeySize() {
        return DEFAULT_KEYSIZE;
    }
    
    @Override
    protected String getDefaultAlgorithm() {
        return DEFAULT_ALGORITHM;
    }
}
