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
     */
    public byte[] toBytes(
            _Type_ actualData);
    
    /**
     * Convert the given data to byte array.
     * 
     * @param data
     *            the data.
     * @return the byte array equivalent of the actual data wrapped by the given data.
     */
    public byte[] toBytes(
            Data<_Type_> data);
    
    /**
     * Constructs the actual data from the given bytes.
     * 
     * @param bytes
     *            the byte array representation of the actual data.
     * @return the actual data constructed from the byte array.
     */
    public _Type_ fromBytes(
            byte[] bytes);
    
    /**
     * Converts the given actual data to the data.
     * 
     * @param actualData
     *            the given actual data.
     * @return the wrapped data.
     */
    public Data<_Type_> toData(
            _Type_ actualData);
    
    /**
     * Converts the given byte array from the actual data to the data.
     * 
     * @param bytes
     *            the byte array.
     * @return the wrapped data.
     */
    public Data<_Type_> toDataFromByte(
            byte[] bytes);
    
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
