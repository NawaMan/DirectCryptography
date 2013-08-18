package dssb.cryptography.signature;

import dssb.cryptography.Data;
import dssb.cryptography.Type;

/**
 * Classes implementing this interface can sign some data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Signer {
    
    /**
     * Returns the signature used.
     * 
     * @return the signature used.
     */
    public Signature getSignature();
    
    /**
     * Sign the given input data in bytes to bytes.
     * 
     * @param data
     *            the input data.
     * @return the fingerprint in bytes.
     * @throws SignException
     *             when there is any problem signing.
     */
    public byte[] signToBytes(
            byte[] data)
            throws SignException;
    
    /**
     * Sign the given input data to bytes.
     * 
     * @param <_Type_>
     *            the type of the input data.
     * @param data
     *            the data.
     * @return the fingerprint in bytes.
     * @throws SignException
     *             when there is any problem signing.
     */
    public <_Type_> byte[] signToBytes(
            final Data<_Type_> data)
            throws SignException;
    
    /**
     * Sign the given input data in bytes to a fingerprint object.
     * 
     * @param data
     *            the input data.
     * @return the fingerprint.
     * @throws SignException
     *             when there is any problem signing.
     */
    public Fingerprint sign(
            byte[] data)
            throws SignException;
    
    /**
     * Sign the given input data to a fingerprint object.
     * 
     * @param <_Type_>
     *            the type of the input data.
     * @param data
     *            the data.
     * @return the fingerprint.
     * @throws SignException
     *             when there is any problem signing.
     */
    public <_Type_> Fingerprint sign(
            final Data<_Type_> data)
            throws SignException;
    
    // == Sub classes ==================================================================================================
    
    /** This class provide a simple implementation common to most {@code Signer}. */
    public abstract static class Simple implements Signer {
        
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
        public <_Type_> byte[] signToBytes(
                final Data<_Type_> data)
                throws SignException {
            final Type<_Type_> type = data.getType();
            final byte[] bytes = type.toBytes(data);
            final byte[] fingerprint = this.signToBytes(bytes);
            return fingerprint;
        }
        
        /** {@inheritDoc} */
        @Override
        public Fingerprint sign(
                final byte[] data)
                throws SignException {
            final byte[] fingerprintBytes = this.signToBytes(data);
            final Fingerprint fingerprint = new Fingerprint.Simple(this.signature, fingerprintBytes);
            return fingerprint;
        }
        
        /** {@inheritDoc} */
        @Override
        public <_Type_> Fingerprint sign(
                final Data<_Type_> data)
                throws SignException {
            final byte[] dataBytes = data.toBytes();
            final Fingerprint fingerprint = this.sign(dataBytes);
            return fingerprint;
        }
    }
    
}
