package dssb.cryptography.schemes.messagedigest;

import dssb.cryptography.hasher.HashException;
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
    public byte[] hash(
            final byte[] data)
            throws HashException {
        final String algorithm = this.getCryptography().getAlgorithm();
        final byte[] hash = MessageDigestScheme._.hash(algorithm, data);
        return hash;
    }
    
}
