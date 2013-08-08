package dssb.cryptography;

/**
 * The type of data involving in the cryptography. A type can convert the data from and to byte array.
 * 
 * @param <_Type_>
 *            the data type.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Type<_Type_> {
    
    /**
     * The class of the data.
     * 
     * @return the class.
     */
    public Class<_Type_> getDataClass();
    
    /**
     * Convert the given actual data to byte array.
     * 
     * @param actualData
     *            the actual data.
     * @return the byte array equivalent of the data.
     * @throws ConvertToBytesException
     *             when the conversion failed.
     */
    public byte[] toBytes(
            final _Type_ actualData)
            throws ConvertToBytesException;
    
    /**
     * Convert the given data to byte array.
     * 
     * @param data
     *            the data.
     * @return the byte array equivalent of the actual data wrapped by the given data.
     * @throws ConvertToBytesException
     *             when the conversion failed.
     */
    public byte[] toBytes(
            final Data<_Type_> data)
            throws ConvertToBytesException;
    
    /**
     * Constructs the actual data from the given bytes.
     * 
     * @param bytes
     *            the byte array representation of the actual data.
     * @return the actual data constructed from the byte array.
     * @throws ConvertFromBytesException
     *             when the conversion failed.
     */
    public _Type_ fromBytes(
            final byte[] bytes)
            throws ConvertFromBytesException;
    
    /**
     * Converts the given actual data to the data.
     * 
     * @param actualData
     *            the given actual data.
     * @return the wrapped data.
     * @throws DataConversionException
     *             when the conversion failed.
     */
    public Data<_Type_> toData(
            final _Type_ actualData)
            throws DataConversionException;
    
    /**
     * Converts the given byte array from the actual data to the data.
     * 
     * @param bytes
     *            the byte array.
     * @return the wrapped data.
     * @throws ConvertFromBytesException
     *             when the conversion failed.
     */
    public Data<_Type_> toDataFromByte(
            final byte[] bytes)
                    throws ConvertFromBytesException;
    
    /**
     * Simple implementation for {@link Type}.
     * 
     * @param <_Type_>
     *            the data type.
     */
    public abstract static class Simple<_Type_> implements Type<_Type_> {
        
        /** {@inheritDoc} **/
        @Override
        public final byte[] toBytes(
                final Data<_Type_> data) {
            final _Type_ raw = data.getData();
            final byte[] bytes = this.toBytes(raw);
            return bytes;
        }
        
        /** {@inheritDoc} **/
        @Override
        public Data<_Type_> toDataFromByte(
                final byte[] bytes) {
            final _Type_ data = this.fromBytes(bytes);
            final Data<_Type_> clearData = this.toData(data);
            return clearData;
        }
        
    }
    
}
