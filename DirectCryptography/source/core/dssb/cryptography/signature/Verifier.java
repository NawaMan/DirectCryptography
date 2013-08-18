package dssb.cryptography.signature;

import dssb.cryptography.Data;

/**
 * Classes implementing this interface can verify signed data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Verifier {
    
    /**
     * Returns the signature used.
     * 
     * @return the signature used.
     */
    public Signature getSignature();
    
    /**
     * Verify if the signed data.
     * 
     * @param dataBytes
     *            the data in bytes.
     * @param fingerprintBytes
     *            the fingerprint in bytes.
     * @return the verification result.
     * @throws VerifyException
     *             when there is a problem verifying.
     */
    public boolean verify(
            final byte[] dataBytes,
            final byte[] fingerprintBytes)
            throws VerifyException;
    
    /**
     * Verify if the signed data.
     * 
     * @param dataBytes
     *            the data in bytes.
     * @param fingerprint
     *            the fingerprint.
     * @return the verification result.
     * @throws VerifyException
     *             when there is a problem verifying.
     */
    public boolean verify(
            final byte[] dataBytes,
            final Fingerprint fingerprint)
            throws VerifyException;
    
    /**
     * Verify if the signed data.
     * 
     * @param <_Type_>
     *            the type of the input data.
     * @param data
     *            the data.
     * @param fingerprintBytes
     *            the fingerprint in bytes.
     * @return the verification result.
     * @throws VerifyException
     *             when there is a problem verifying.
     */
    public <_Type_> boolean verify(
            final Data<_Type_> data,
            final byte[] fingerprintBytes)
            throws VerifyException;
    
    /**
     * Verify if the signed data.
     * 
     * @param <_Type_>
     *            the type of the input data.
     * @param data
     *            the data.
     * @param fingerprint
     *            the fingerprint.
     * @return the verification result.
     * @throws VerifyException
     *             when there is a problem verifying.
     */
    public <_Type_> boolean verify(
            final Data<_Type_> data,
            final Fingerprint fingerprint)
            throws VerifyException;
    
    // == Sub classes ==================================================================================================
    
    /** This class provide a simple implementation common to most {@code Verifier}. */
    public abstract static class Simple implements Verifier {
        
        /** The signature used. */
        private final Signature signature;
        
        /**
         * Constructor.
         * 
         * @param signature
         *            the signature.
         */
        public Simple(
                final Signature signature) {
            this.signature = signature;
        }
        
        /**
         * Returns the signature.
         * 
         * @return the signature.
         **/
        public final Signature getSignature() {
            return this.signature;
        }
        
        /** {@inheritDoc} */
        @Override
        public boolean verify(
                final byte[] dataBytes,
                final Fingerprint fingerprint)
                throws VerifyException {
            final byte[] fingerprintBytes = fingerprint.getBytes();
            final boolean isVerified = this.verify(dataBytes, fingerprintBytes);
            return isVerified;
        }
        
        /** {@inheritDoc} */
        @Override
        public <_Type_> boolean verify(
                final Data<_Type_> data,
                final byte[] fingerprintBytes)
                throws VerifyException {
            final byte[] bytes = data.toBytes();
            final boolean isVerified = this.verify(bytes, fingerprintBytes);
            return isVerified;
        }
        
        /** {@inheritDoc} */
        @Override
        public <_Type_> boolean verify(
                final Data<_Type_> data,
                final Fingerprint fingerprint)
                throws VerifyException {
            final byte[] dataBytes = data.toBytes();
            final byte[] fingerprintBytes = fingerprint.getBytes();
            final boolean isVerified = this.verify(dataBytes, fingerprintBytes);
            return isVerified;
        }
        
    }
    
}
