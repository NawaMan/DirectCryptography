package dssb.cryptography.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import dssb.cryptography.Cryptography;
import dssb.cryptography.Scheme;
import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.encoder.EncoderFactory;
import dssb.cryptography.hasher.HasherFactory;
import dssb.cryptography.signature.Signature;

/**
 * Abstract implementation cryptography.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public abstract class AbstractCryptography implements Cryptography {
    
    /** The cryptography scheme. */
    private final Scheme scheme;
    
    /**
     * Constructor.
     * 
     * @param scheme
     *            the cryptography scheme.
     */
    protected AbstractCryptography(
            final Scheme scheme) {
        this.scheme = scheme;
    }
    
    /** {@inheritDoc} */
    @Override
    public Scheme getScheme() {
        return this.scheme;
    }
    
    /** {@inheritDoc} */
    @Override
    public Collection<Feature<?>> getFeatures() {
        final List<Feature<?>> list = new ArrayList<Feature<?>>();
        
        final Cipher cipher = this.newCipher();
        if (cipher != null) {
            list.add(cipher);
        }
        final Signature signature = this.newSignature();
        if (signature != null) {
            list.add(signature);
        }
        final HasherFactory hasherFactory = this.newHasherFactory();
        if (hasherFactory != null) {
            list.add(hasherFactory);
        }
        final EncoderFactory encoderFactory = this.newEncoderFactory();
        if (encoderFactory != null) {
            list.add(encoderFactory);
        }
        
        return Collections.unmodifiableList(list);
    }
    
    /** {@inheritDoc} */
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
     * Create and return a cipher.
     * 
     * @return a newly created cipher.
     **/
    protected Cipher newCipher() {
        return null;
    }
    
    /**
     * Create and return a signature.
     * 
     * @return a newly created signature.
     **/
    protected Signature newSignature() {
        return null;
    }
    
    /**
     * Create and return a hasher factory.
     * 
     * @return a newly created hasher factory.
     **/
    protected HasherFactory newHasherFactory() {
        return null;
    }
    
    /**
     * Create and return a encoder factory.
     * 
     * @return a newly created encoder factory.
     **/
    protected EncoderFactory newEncoderFactory() {
        return null;
    }
    
}
