package dssb.cryptography.encoder;

import dssb.cryptography.Cryptography;
import dssb.cryptography.Cryptography.Feature;

/**
 * This feature of a {@link Cryptography} can create a {@link Encoder} used to encode the data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface EncoderFactory extends Feature<EncoderFactory> {
    
    /**
     * Returns the encoder.
     * 
     * @return the encoder.
     */
    public Encoder getEncoder();
    
    // == Sub classes ==================================================================================================
    
    /** This class provide a simple implementation common to most {@code Encoder}. */
    public abstract static class Simple implements EncoderFactory {
        
        /** The {@code Cryptography}. */
        private final Cryptography cryptography;
        
        /** The {@code Encoder}. */
        private volatile Encoder encoder = null;
        
        /**
         * Constructor.
         * 
         * @param cryptography
         *            the {@code Cryptography} used by this {@code Cipher}.
         */
        public Simple(
                final Cryptography cryptography) {
            this.cryptography = cryptography;
        }
        
        /** {@inheritDoc} **/
        @Override
        public Cryptography getCryptography() {
            return this.cryptography;
        }
        
        /** {@inheritDoc} **/
        @Override
        public Encoder getEncoder() {
            if (this.encoder != null) {
                return this.encoder;
            }
            
            synchronized (this) {
                if (this.encoder != null) {
                    return this.encoder;
                }
                
                this.encoder = this.newEncoder();
                return this.encoder;
            }
        }
        
        /**
         * Create a new {@code Encoder}.
         * 
         * @return a new {@code Encoder}.
         */
        public abstract Encoder newEncoder();
        
        /**
         * Encode the given data in bytes.
         * 
         * @param data
         *            the data.
         * @return the encoded string.
         */
        public abstract String encode(
                byte[] data);
        
        /**
         * Decode the given string to data in bytes.
         * 
         * @param encodedString
         *            the encoded string.
         * @return the encoded bytes        .
         */
        public abstract byte[] decode(
                String encodedString);
        
    }
    
}
