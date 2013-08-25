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
    
    /**
     * This class provide a simple implementation common to most {@code Encoder}.
     * 
     * @param <_Cryptography_>
     *            the cryptography.
     **/
    public abstract static class Simple<_Cryptography_ extends Cryptography> implements EncoderFactory {
        
        /**
         * This {@link Encoder} implementation is used with {@code EncoderFactory.Simple}.
         * 
         * @param <_Cryptography_>
         *            the cryptography.
         **/
        abstract public static class Encoder<_Cryptography_ extends Cryptography> extends dssb.cryptography.encoder.Encoder.Simple {
            
            /**
             * Constructs an encryptor for the given {@code HasherFactory}.
             * 
             * @param encoderFactory
             *            factory the encoder factory.
             */
            public Encoder(
                    final EncoderFactory.Simple<_Cryptography_> encoderFactory) {
                super(encoderFactory);
            }
            
        }
        
        /** The {@code Cryptography}. */
        private final _Cryptography_ cryptography;
        
        /** The {@code Encoder}. */
        private volatile Encoder<_Cryptography_> encoder = null;
        
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
        public Encoder<_Cryptography_> getEncoder() {
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
        public Encoder<_Cryptography_> newEncoder() {
            return new Simple.Encoder<_Cryptography_>(this) {
                @Override
                public String encode(
                        final byte[] data)
                        throws EncodeException {
                    final String encStr = EncoderFactory.Simple.this.encode(data);
                    return encStr;
                }
                @Override
                public byte[] decode(
                        final String encodedString)
                        throws DecodeException {
                    final byte[] bytes = EncoderFactory.Simple.this.decode(encodedString);
                    return bytes;
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
         * @return the encoded bytes.
         */
        public abstract byte[] decode(
                String encodedString);
        
    }
    
}
