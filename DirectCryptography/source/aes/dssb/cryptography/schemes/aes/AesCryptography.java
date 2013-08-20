package dssb.cryptography.schemes.aes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.crypto.SecretKey;

import dssb.cryptography.Cryptography;
import dssb.cryptography.Scheme;
import dssb.cryptography.cipher.Cipher;

public class AesCryptography implements Cryptography {
    
    private final SecretKey secretKey;
    
    private final Scheme scheme;
    
    public AesCryptography(
            final SecretKey secretKey) {
        this.scheme = AesScheme.INSTANCE;
        this.secretKey = secretKey;
    }
    @Override
    public Scheme getScheme() {
        return this.scheme;
    }
    
    @Override
    public Collection<Feature<?>> getFeatures() {
        final AesCipher cipher = this.newCipher();
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
    protected AesCipher newCipher() {
        return new AesCipher(this, this.secretKey);
    }
    
}
