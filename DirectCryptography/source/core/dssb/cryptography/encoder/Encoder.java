package dssb.cryptography.encoder;

import dssb.cryptography.Data;
import dssb.cryptography.Type;

/**
 * Classes implementing this interface can encode the given data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Encoder {
    
    /**
     * The factory that build this encoder.
     * 
     * @return the factory.
     */
    public EncoderFactory getEncoderFactory();
    
    /**
     * Encode the given data in bytes.
     * 
     * @param data
     *            the data.
     * @return the encoded string.
     * @throws EncodeException
     *             when there is a problem encoding the data.
     */
    public String encode(
            byte[] data)
            throws EncodeException;
    
    /**
     * Encode the given data in bytes.
     * 
     * @param <_Type_>
     *            the data type.
     * @param data
     *            the data.
     * @return the encoded string.
     * @throws EncodeException
     *             when there is a problem encoding the data.
     */
    public <_Type_> String encode(
            Data<_Type_> data)
            throws EncodeException;
    
    /**
     * Decode the given string to data in bytes.
     * 
     * @param encodedString
     *            the encoded string.
     * @return the encoded bytes .
     * @throws DecodeException
     *             when there is a problem decoding the data.
     */
    public byte[] decode(
            String encodedString)
            throws DecodeException;
    
    /**
     * Decode the given string to data in bytes.
     * 
     * @param <_Type_>
     *            the type of the data.
     * @param encodedString
     *            the encoded string.
     * @param dataType
     *            the data type.
     * @return the encoded bytes .
     * @throws DecodeException
     *             when there is a problem decoding the data.
     */
    public <_Type_> Data<_Type_> decode(
            String encodedString,
            Type<_Type_> dataType)
            throws DecodeException;
    
    // == Sub classes ==================================================================================================
    
    /** This class provide a simple implementation common to most {@code Encoder}. */
    public abstract static class Simple implements Encoder {
        
        /** The {@code HasherFactory}. */
        private final EncoderFactory encoderFactory;
        
        /**
         * Constructor.
         * 
         * @param encoderFactory
         *            the {@code EncoderFactory} used by this {@code Encoder}.
         */
        public Simple(
                final EncoderFactory encoderFactory) {
            this.encoderFactory = encoderFactory;
        }
        
        /** {@inheritDoc} **/
        @Override
        public EncoderFactory getEncoderFactory() {
            return this.encoderFactory;
        }
        
        /** {@inheritDoc} **/
        @Override
        public <_Type_> String encode(
                final Data<_Type_> data)
                throws EncodeException {
            final byte[] bytes = data.toBytes();
            final String encStr = this.encode(bytes);
            return encStr;
        }
        
        /** {@inheritDoc} **/
        @Override
        public <_Type_> Data<_Type_> decode(
                final String encodedString,
                final Type<_Type_> dataType)
                throws DecodeException {
            final byte[] bytes = this.decode(encodedString);
            final Data<_Type_> data = dataType.toDataFromByte(bytes);
            return data;
        }
        
    }
    
}
