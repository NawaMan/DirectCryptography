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
    public abstract static class Simple<_Cryptography_ extends Cryptography> implements EncoderFactory {
        
        /** The {@code Cryptography}. */
        private final _Cryptography_ cryptography;
        
        /** The {@code Encoder}. */
        private volatile Encoder encoder = null;
        
        /**
         * Constructor.
         * 
         * @param cryptography
         *            the {@code Cryptography} used by this {@code Cipher}.
         */
        public Simple(
                final _Cryptography_ cryptography) {
            this.cryptography = cryptography;
        }
        
        /** {@inheritDoc} **/
        @Override
        public _Cryptography_ getCryptography() {
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
        public Encoder newEncoder() {
            return new Encoder() {
                
                @Override
                public EncoderFactory getEncoderFactory() {
                    return EncoderFactory.Simple.this;
                }
                
                @Override
                public String encode(
                        final byte[] data) {
                    return EncoderFactory.Simple.this.encode(data);
                }
                
                @Override
                public byte[] decode(
                        final String encodedString) {
                    return EncoderFactory.Simple.this.decode(encodedString);
                }
            };
        }
        
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
