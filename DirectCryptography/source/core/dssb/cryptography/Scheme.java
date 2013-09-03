package dssb.cryptography;

/**
 * Cryptography scheme.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Scheme {
    
    /**
     * Creates and return a new cryptography builder.
     * 
     * @param <_Builder_>
     *            the cryptography builder.
     * 
     * @return the builder.
     */
    public <_Builder_ extends CryptographyBuilder> _Builder_ createCryptographyBuilder();
    
}
