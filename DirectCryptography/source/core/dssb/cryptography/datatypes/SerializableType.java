package dssb.cryptography.datatypes;

import java.io.Serializable;

import dssb.cryptography.Data;
import dssb.cryptography.Type;
import dssb.cryptography.util.UBytes;

/**
 * Data type for serializable object.
 * 
 * @param <_Type_>
 *            the type (class).
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class SerializableType<_Type_ extends Serializable> extends Type.Simple<_Type_> {
    
    /** The data class. */
    private final Class<_Type_> type;
    
    /**
     * Constructs a type.
     * 
     * @param type
     *            the actual data type.
     */
    public SerializableType(
            final Class<_Type_> type) {
        if (type == null) {
            throw new NullPointerException();
        }
        this.type = type;
    }
    
    /** {@inheritDoc} */
    @Override
    public Class<_Type_> getDataClass() {
        return this.type;
    }
    
    /** {@inheritDoc} */
    @Override
    public byte[] toBytes(
            final _Type_ data) {
        final byte[] bytes = UBytes._.serializableToBytes(data);
        return bytes;
    }
    
    /** {@inheritDoc} */
    @Override
    public _Type_ fromBytes(
            final byte[] bytes) {
        final _Type_ data = UBytes._.bytesToSerializable(bytes, this.type);
        return data;
    }
    
    /** {@inheritDoc} */
    @Override
    public Data<_Type_> toData(
            final _Type_ data) {
        return new Data.Simple<_Type_>(this, data);
    }
    
}
