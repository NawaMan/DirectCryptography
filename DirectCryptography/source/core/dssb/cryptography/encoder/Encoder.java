package dssb.cryptography.encoder;

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
     */
    public String encode(
            byte[] data);
    
    /**
     * Decode the given string to data in bytes.
     * 
     * @param encodedString
     *            the encoded string.
     * @return the encoded bytes        .
     */
    public byte[] decode(
            String encodedString);
    
}
