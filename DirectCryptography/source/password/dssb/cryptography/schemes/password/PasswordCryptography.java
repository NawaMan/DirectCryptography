package dssb.cryptography.schemes.password;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.crypto.SecretKey;

import dssb.cryptography.Cryptography;
import dssb.cryptography.Scheme;
import dssb.cryptography.cipher.Cipher;

public class PasswordCryptography implements Cryptography {
    
    private final SecretKey secretKey;
    
    private final Scheme scheme;
    
    public PasswordCryptography(
            final SecretKey secretKey) {
        this.scheme = PasswordScheme.INSTANCE;
        this.secretKey = secretKey;
    }
    @Override
    public Scheme getScheme() {
        return this.scheme;
    }
    
    @Override
    public Collection<Feature<?>> getFeatures() {
        final PasswordCipher cipher = this.newCipher();
        final List<Feature<?>> list = new ArrayList<Feature<?>>();
        list.add(cipher);
        return list;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <_Feature_ extends Feature<_Feature_>> _Feature_ getFeature(
            final Class<_Feature_> featureClass) {
        if (Cipher.class.isAssignableFrom(featureClass)) {
            return (_Feature_) this.newCipher();
        }
        
        return null;
    }
    
    /**
     * Creates a new cipher.
     * 
     * @return a newly created {@link Cipher}.
     */
    protected PasswordCipher newCipher() {
        return new PasswordCipher(this, this.secretKey);
    }
    
}
