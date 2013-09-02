package dssb.cryptography.schemes.messagedigest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dssb.cryptography.Scheme;
import dssb.cryptography.hasher.HashException;

/**
 * Cryptography scheme for message digest.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public enum MessageDigestScheme implements Scheme {
    
    /** Shorthand instance. */
    _,
    
    /** Static import instance. */
    MessageDigestScheme,
    
    /** Conventional instance. */
    INSTANCE;
    
    @Override
    public MessageDigestCryptographyBuilder createCryptographyBuilder() {
        return new MessageDigestCryptographyBuilder();
    }
    
    /**
     * Calculate hash of the given data using the algorithm name.
     * 
     * @param algorithm
     *            the algorithm name.
     * @param data
     *            the input data.
     * @return the bytes of hash value.
     * @throws HashException
     *             problem with calculating hash value.
     **/
    public byte[] hash(
            final String algorithm,
            final byte[] data)
            throws HashException {
        try {
            final MessageDigest md = MessageDigest.getInstance(algorithm);
            final byte[] hash = md.digest(data);
            return hash;
        } catch (final NoSuchAlgorithmException problem) {
            final Throwable unknownAlgorithm = new UnknownMessageDigestAlgorithmException(algorithm, problem);
            final HashException hashProblem = new HashException(unknownAlgorithm);
            throw hashProblem;
        }
    }
}
