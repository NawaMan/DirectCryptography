package dssb.cryptography.schemes.sha1rsa;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dssb.cryptography.schemes.rsa.RsaCryptography;
import dssb.cryptography.signature.Signature;

public class Sha1RsaCryptography extends RsaCryptography {
        
    private final RsaCryptography rsaCryptography;
    
    private final PublicKey publicKey;
    
    private final PrivateKey privateKey;
    
    public Sha1RsaCryptography(
            final RsaCryptography rsaCryptography,
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(privateKey, publicKey);
        this.rsaCryptography = rsaCryptography;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
    
    @Override
    public Collection<Feature<?>> getFeatures() {
        final List<Feature<?>> list = new ArrayList<Feature<?>>();
        final Sha1RsaSignature signature = this.newSignature();
        list.add((signature != null) ? signature : null);
        return list;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <_Feature_ extends Feature<_Feature_>> _Feature_ getFeature(
            final Class<_Feature_> featureClass) {
        if (Signature.class.isAssignableFrom(featureClass)) {
            return (_Feature_) this.newSignature();
        }
        
        return null;
    }
    
    /**
     * Creates a new cipher.
     * 
     * @return a newly created {@link Signature}.
     */
    protected Sha1RsaSignature newSignature() {
        final PrivateKey privateKey = this.privateKey;
        final PublicKey publicKey = this.publicKey;
        final Sha1RsaSignature signature = new Sha1RsaSignature(this, privateKey, publicKey);
        return signature;
    }
    
}
