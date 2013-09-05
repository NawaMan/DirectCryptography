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
 * Common implementation for {@code Cryptography}.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public abstract class AbstractCryptography implements Cryptography {
    
    /** The scheme. */
    private final Scheme scheme;
    
    /**
     * Constructor.
     * 
     * @param scheme
     *            the scheme.
     **/
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
        
        final List<Feature<?>> outList = Collections.unmodifiableList(list);
        return outList;
    }
    
    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public <_Feature_ extends Feature<_Feature_>> _Feature_ getFeature(
            final Class<_Feature_> featureClass) {
        if (Cipher.class.isAssignableFrom(featureClass)) {
            return (_Feature_) this.newCipher();
        }
        if (Signature.class.isAssignableFrom(featureClass)) {
            return (_Feature_) this.newSignature();
        }
        if (HasherFactory.class.isAssignableFrom(featureClass)) {
            return (_Feature_) this.newHasherFactory();
        }
        if (EncoderFactory.class.isAssignableFrom(featureClass)) {
            return (_Feature_) this.newEncoderFactory();
        }
        
        return null;
    }
    
    /**
     * Create and return a {@link Cipher} from this {@code Cryptography}.
     * 
     * @return a new {@link Cipher}.
     **/
    protected Cipher newCipher() {
        return null;
    }
    
    /**
     * Create and return a {@link Signature} from this {@code Cryptography}.
     * 
     * @return a new {@link Signature}.
     **/
    protected Signature newSignature() {
        return null;
    }
    
    /**
     * Create and return a {@link HasherFactory} from this {@code Cryptography}.
     * 
     * @return a new {@link HasherFactory}.
     **/
    protected HasherFactory newHasherFactory() {
        return null;
    }
    
    /**
     * Create and return a {@link EncoderFactory} from this {@code Cryptography}.
     * 
     * @return a new {@link EncoderFactory}.
     **/
    protected EncoderFactory newEncoderFactory() {
        return null;
    }
    
}
