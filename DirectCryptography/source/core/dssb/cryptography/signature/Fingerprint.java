package dssb.cryptography.signature;

/**
 * Classes implementing this interface represent digital fingerprint.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Fingerprint {
    
    /**
     * Returns the signature used.
     * 
     * @return the signature used.
     */
    public Signature getSignature();
    
    /**
     * Return the byte data of the figerprint.
     * 
     * @return the byte data of the figerprint.
     */
    public byte[] getBytes();
    
    // == Sub classes ==================================================================================================
    
    /** This class provide a simple implementation common to most {@code Fingerprint}. */
    public static class Simple implements Fingerprint {
        
        /** The data in bytes. */
        private final byte[] bytes;
        
        /** The signature used. */
        private final Signature signature;
        
        /**
         * Constructor.
         * 
         * @param signature
         *            the signature.
         * @param fingerprintBytes
         *            the digital fingerprint in bytes.
         */
        public Simple(
                final Signature signature,
                final byte[] fingerprintBytes) {
            if (signature == null) {
                throw new NullPointerException();
            }
            this.signature = signature;
            this.bytes = fingerprintBytes.clone();
        }
        
        /** {@inheritDoc} */
        @Override
        public final Signature getSignature() {
            // TODO Auto-generated method stub
            return this.signature;
        }
        
        /** {@inheritDoc} */
        @Override
        public byte[] getBytes() {
            return this.bytes.clone();
        }
    }
}
