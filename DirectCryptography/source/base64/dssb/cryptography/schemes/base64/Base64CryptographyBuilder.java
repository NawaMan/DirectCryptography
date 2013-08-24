package dssb.cryptography.schemes.base64;

import dssb.cryptography.CryptographyBuilder;

/**
 * Builder for {@link Base64Cryptography}.
 * 
 * This builder will take an algorithm name.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class Base64CryptographyBuilder implements CryptographyBuilder {
    
    /** {@inheritDoc} */
    @Override
    public Base64Cryptography newCryptography() {
        return new Base64Cryptography();
    }

}
