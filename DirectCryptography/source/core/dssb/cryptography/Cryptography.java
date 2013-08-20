package dssb.cryptography;

import java.util.Collection;

/**
 * Classes implement this class represent cryptography information needed to do cryptography operations.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Cryptography {
    
    /**
     * Returns the cryptography scheme for this chryptography.
     * 
     * @return the scheme.
     */
    public Scheme getScheme();
    
    /**
     * Returns all features for this {@link Cryptography}.
     * 
     * @return all the available features.
     */
    public Collection<Feature<?>> getFeatures();
    
    /**
     * Returns a feature of the given class.
     * 
     * @param <_Feature_>
     *            the feature.
     * @param featureClass
     *            the feature class.
     * @return the feature.
     */
    public <_Feature_ extends Feature<_Feature_>> _Feature_ getFeature(
            Class<_Feature_> featureClass);
    
    // == Sub classes ==================================================================================================
    
    /**
     * Feature for {@link Cryptography}.
     * 
     * @param <_Feature_>
     *            the feature class.
     **/
    public static interface Feature<_Feature_ extends Feature<_Feature_>> {
        
        /**
         * Returns the {@link Cryptography}.
         * 
         * @return the {@link Cryptography}.
         */
        public Cryptography getCryptography();
        
    }
    
    // TODO
    // Save and Store
    // See http://snipplr.com/view/18368/
    // See http://stackoverflow.com/questions/5263156/rsa-keypair-generation-and-storing-to-keystore
    // See http://stackoverflow.com/questions/13894699/java-how-to-store-a-key-in-keystore
    
}
