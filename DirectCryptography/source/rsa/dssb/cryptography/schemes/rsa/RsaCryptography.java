package dssb.cryptography.schemes.rsa;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dssb.cryptography.Cryptography;
import dssb.cryptography.Scheme;
import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.schemes.aes.AesScheme;

public class RsaCryptography implements Cryptography {
    
    private final Scheme scheme;
    
    private PrivateKey privateKey = null;
    
    private PublicKey publicKey = null;
    
    public RsaCryptography(
            final KeyPair keyPair) {
        this(keyPair.getPrivate(), keyPair.getPublic());
    }
    
    public RsaCryptography(
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        this.scheme = AesScheme.INSTANCE;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
    
    @Override
    public Scheme getScheme() {
        return this.scheme;
    }
    
    @Override
    public Collection<Feature<?>> getFeatures() {
        final RsaCipher cipher = this.newCipher();
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
    protected RsaCipher newCipher() {
        return new RsaCipher(this, this.privateKey, this.publicKey);
    }
    
}
