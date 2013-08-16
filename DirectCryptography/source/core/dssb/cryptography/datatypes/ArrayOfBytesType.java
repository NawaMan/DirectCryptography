package dssb.cryptography.datatypes;

import dssb.cryptography.Data;
import dssb.cryptography.util.DataConversionException;
import dssb.cryptography.util.UBytes;


/**
 * Type for array of byte array.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class ArrayOfBytesType extends dssb.cryptography.Type.Simple<byte[][]> {
    
    /** The type instance. */
    public static final ArrayOfBytesType TYPE = new ArrayOfBytesType();
    
    /** {@inheritDoc} */
    @Override
    public final Class<byte[][]> getDataClass() {
        return byte[][].class;
    }
    
    /** {@inheritDoc} */
    @Override
    public byte[] toBytes(
            final byte[]... data) {
        final byte[] bytes = UBytes._.bytesArrayToBytes(data);
        return bytes;
    }
    
    /** {@inheritDoc} */
    @Override
    public byte[][] fromBytes(
            final byte[] bytes) {
        final byte[][] byteArray = UBytes._.bytesToBytesArray(bytes);
        return byteArray;
    }
    
    @Override
    public Data<byte[][]> toData(
            final byte[][] actualData)
            throws DataConversionException {
        final Data<byte[][]> data = new Data.Simple<byte[][]>(this, actualData);
        return data;
    }
}
