package dssb.cryptography.encoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dssb.cryptography.Cryptography;
import dssb.cryptography.Scheme;

/**
 * This encoder cryptography implementation contains common functionalities.
 * 
 * NOTE: For now it contains functionality for providing features.
 * 
 * @param <_Scheme_>
 *            the cryptography scheme.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public abstract class AbstractEncoderCryptography<_Scheme_ extends Scheme> implements Cryptography {
    
    /**
     * Returns the cryptography scheme.
     * 
     * @return the cryptography scheme.
     */
    public abstract _Scheme_ getScheme();
    
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
     * Returns the encoder factory.
     * 
     * @return the encoder factory.
     **/
    protected abstract EncoderFactory newEncoderFactory();
    
}
