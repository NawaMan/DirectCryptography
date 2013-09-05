package dssb.cryptography.schemes.messagedigest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dssb.cryptography.CryptographyBuildFailException;
import dssb.cryptography.Scheme;
import dssb.cryptography.common.AbstractCryptography;
import dssb.cryptography.hasher.HasherFactory;

/**
 * Cryptography for Message digest.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class MessageDigestCryptography extends AbstractCryptography {
    
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
        this(MessageDigestScheme.INSTANCE, algorithm);
    }
    /**
     * Constructor.
     * 
     * NOTE: This overload constructor allows this class to be inherited.
     * 
     * @param scheme
     *            the scheme.
     * @param algorithm
     *            the algorithm.
     */
    protected MessageDigestCryptography(
            final Scheme scheme,
            final String algorithm) {
        super(scheme);
        this.algorithm = algorithm;
        try {
            MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException problem) {
            final Exception configurationProblem = new UnknownMessageDigestAlgorithmException(algorithm, problem);
            throw new CryptographyBuildFailException(configurationProblem);
        }
    }
    
    /**
     * Returns the algorithm.
     * 
     * @return the algorithm.
     **/
    public String getAlgorithm() {
        return this.algorithm;
    }
    
    /** {@inheritDoc} */
    @Override
    protected HasherFactory newHasherFactory() {
        final HasherFactory hasherFactory = new MessageDigestHasherFactory(this);
        return hasherFactory;
    }
    
}
