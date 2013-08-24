package dssb.cryptography.schemes.base64;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dssb.cryptography.Cryptography;
import dssb.cryptography.Scheme;
import dssb.cryptography.encoder.EncoderFactory;
import dssb.cryptography.hasher.HasherFactory;

/**
 * Cryptography for Base64.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class Base64Cryptography implements Cryptography {
    
    /** {@inheritDoc} */
    @Override
    public Scheme getScheme() {
        return Base64Scheme.INSTANCE;
    }
    
    /** {@inheritDoc} */
    @Override
    public Collection<Feature<?>> getFeatures() {
        final List<Feature<?>> list = new ArrayList<Feature<?>>();
        final EncoderFactory encoderFactory = this.newEncoderFactory();
        list.add((encoderFactory != null)
                ? encoderFactory
                : null);
        return list;
    }
    
    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public <_Feature_ extends Feature<_Feature_>> _Feature_ getFeature(
            final Class<_Feature_> featureClass) {
        if (EncoderFactory.class.isAssignableFrom(featureClass)) {
            return (_Feature_) this.newEncoderFactory();
        }
        
        return null;
    }
    
    /**
     * Creates a new {@link HasherFactory}.
     * 
     * @return a newly created {@link HasherFactory}.
     */
    protected EncoderFactory newEncoderFactory() {
        final EncoderFactory encoderFactory = new Base64EncoderFactory(this);
        return encoderFactory;
    }
    
}
