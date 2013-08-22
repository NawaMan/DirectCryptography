package dssb.cryptography.schemes.messagedigest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dssb.cryptography.Cryptography;
import dssb.cryptography.CryptographyBuildFailException;
import dssb.cryptography.Scheme;
import dssb.cryptography.hasher.HasherFactory;

/**
 * Cryptography for Message digest.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class MessageDigestCryptography implements Cryptography {
    
    /** The algorithm. */
    private final String algorithm;
    
    /**
     * Constructor.
     * 
     * @param algorithm
     *            the algorithm.
     */
    public MessageDigestCryptography(
            final String algorithm) {
        this.algorithm = algorithm;
        try {
            MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException problem) {
            final Exception configurationProblem = new UnknownMessageDigestAlgorithmException(algorithm, problem);
            throw new CryptographyBuildFailException(configurationProblem);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public Scheme getScheme() {
        return MessageDigestScheme.INSTANCE;
    }
    
    /** {@inheritDoc} */
    @Override
    public Collection<Feature<?>> getFeatures() {
        final List<Feature<?>> list = new ArrayList<Feature<?>>();
        final HasherFactory hasherFactory = this.newHasherFactory();
        list.add((hasherFactory != null)
                ? hasherFactory
                : null);
        return list;
    }
    
    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public <_Feature_ extends Feature<_Feature_>> _Feature_ getFeature(
            final Class<_Feature_> featureClass) {
        if (HasherFactory.class.isAssignableFrom(featureClass)) {
            return (_Feature_) this.newHasherFactory();
        }
        
        return null;
    }
    
    /**
     * Returns the algorithm.
     * 
     * @return the algorithm.
     **/
    public String getAlgorithm() {
        return this.algorithm;
    }
    
    /**
     * Creates a new {@link HasherFactory}.
     * 
     * @return a newly created {@link HasherFactory}.
     */
    protected HasherFactory newHasherFactory() {
        final HasherFactory hasherFactory = new MessageDigestHasherFactory(this);
        return hasherFactory;
    }
    
}
