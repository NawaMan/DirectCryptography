package dssb.cryptography.schemes.messagedigest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dssb.cryptography.CryptographyBuilder;

/**
 * Builder for {@link MessageDigestCryptography}.
 * 
 * This builder will take an algorithm name.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class MessageDigestCryptographyBuilder implements CryptographyBuilder {
    
    /** MD2 algorithm. */
    public static final String MD2 = "MD2";
    
    /** MD5 algorithm. */
    public static final String MD5 = "MD5";
    
    /** SHA-1 algorithm. */
    public static final String SHA1 = "SHA-1";
    
    /** SHA-256 algorithm. */
    public static final String SHA256 = "SHA-256";
    
    /** SHA-384 algorithm. */
    public static final String SHA384 = "SHA-384";
    
    /** SHA-512 algorithm. */
    public static final String SHA512 = "SHA-512";
    
    /** The algorithm. */
    private String algorithm = SHA512;
    
    /**
     * Set the algorithm.
     * 
     * @param algorithm
     *            the algorithm.
     */
    public void setAlgorithm(
            final String algorithm) {
        try {
            MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException problem) {
            throw new UnknownMessageDigestAlgorithmException(algorithm, problem);
        }
        
        this.algorithm = algorithm;
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
     * Use SHA512 algorithm.
     * 
     * @return the Cryptography for SHA512 algorithm.
     */
    public MessageDigestCryptography sha512() {
        this.setAlgorithm(SHA512);
        final MessageDigestCryptography cryptography = this.newCryptography();
        return cryptography;
    }
    
    /** {@inheritDoc} */
    @Override
    public MessageDigestCryptography newCryptography() {
        return new MessageDigestCryptography(this.algorithm);
    }
    
}
