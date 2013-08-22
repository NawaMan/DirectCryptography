package dssb.cryptography.schemes.messagedigest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dssb.cryptography.hasher.HasherFactory;

/**
 * Hasher factory for message digest.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class MessageDigestHasherFactory extends HasherFactory.Simple {
    
    /**
     * Constructor.
     * 
     * @param cryptography
     *            the cryptography;
     */
    public MessageDigestHasherFactory(
            final MessageDigestCryptography cryptography) {
        super(cryptography);
    }
    
    /** {@inheritDoc} */
    @Override
    public MessageDigestCryptography getCryptography() {
        return (MessageDigestCryptography) this.getCryptography();
    }
    
    /** {@inheritDoc} */
    @Override
    public <_Type_> byte[] hash(
            byte[] data) {
        final String algorithm = this.getCryptography().getAlgorithm();
        try {
            final MessageDigest md = MessageDigest.getInstance(algorithm);
            final byte[] hash = md.digest(data);
            return hash;
        } catch (final NoSuchAlgorithmException problem) {
            throw new UnknownMessageDigestAlgorithmException(algorithm, problem);
        }
    }
    
}
