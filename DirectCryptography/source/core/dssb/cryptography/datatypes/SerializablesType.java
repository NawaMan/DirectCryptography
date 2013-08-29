package dssb.cryptography.datatypes;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.concurrent.ConcurrentHashMap;

import dssb.cryptography.Data;
import dssb.cryptography.Type;
import dssb.cryptography.util.DataConversionException;
import dssb.cryptography.util.UBytes;

/**
 * Data type for array of serializable objects.
 * 
 * @param <_Type_>
 *            the type of serializable object.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class SerializablesType<_Type_ extends Serializable> extends dssb.cryptography.Type.Simple<_Type_[]> {
    
    /** The cache for type based on the array component type. */
    @SuppressWarnings({ "rawtypes", "serial" })
    private static final ConcurrentHashMap CACHE = new ConcurrentHashMap<Class, SerializablesType>() {
        @SuppressWarnings("unchecked")
        @Override
        public SerializablesType get(
                final Object key) {
            final SerializablesType currType = super.get(key);
            if (currType != null) {
                return currType;
            }
            
            final Class clzz = (Class) key;
            final SerializablesType newType = new SerializablesType<Serializable>(clzz);
            this.put(clzz, newType);
            return newType;
        }
    };
    
    /**
     * Get type for array of serializable class.
     * 
     * @param clazz
     *            the serializable class.
     * @return the type.
     * @param <T>
     *            the class.
     */
    public static <T extends Serializable> SerializablesType<T> forClazz(
            final Class<T> clazz) {
        @SuppressWarnings("unchecked")
        final SerializablesType<T> type = (SerializablesType<T>) CACHE.get(clazz);
        return type;
    }
    
    /** The class. */
    private final Class<_Type_> clazz;
    
    /** The array class. */
    private final Class<_Type_[]> arrayClazz;
    
    /** The type for the array class. */
    private final Type<_Type_[]> type;
    
    /**
     * Constructs the type.
     * 
     * @param clazz
     *            the class of the object in the array.
     */
    @SuppressWarnings("unchecked")
    public SerializablesType(
            final Class<_Type_> clazz) {
        this.clazz = clazz;
        this.arrayClazz = (Class<_Type_[]>) Array.newInstance(clazz, 0).getClass();
        this.type = new SerializableType<_Type_[]>(this.arrayClazz);
    }
    
    /** {@inheritDoc} */
    @Override
    public Class<_Type_[]> getDataClass() {
        return this.arrayClazz;
    }
    
    /** {@inheritDoc} */
    @Override
    public byte[] toBytes(
            final _Type_... data) {
        final byte[] bytes = UBytes._.serializablesToBytes(data);
        return bytes;
    }
    
    /** {@inheritDoc} */
    @Override
    public _Type_[] fromBytes(
            final byte[] bytes) {
        final _Type_[] byteArray = UBytes._.bytesToSerializables(bytes, this.clazz);
        return byteArray;
    }
    
    @Override
    public Data<_Type_[]> toData(
            final _Type_[] actualData)
            throws DataConversionException {
        final Data<_Type_[]> data = this.type.toData(actualData);
        return data;
    }
    
}
