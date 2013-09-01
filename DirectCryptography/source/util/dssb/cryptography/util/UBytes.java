package dssb.cryptography.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Utility functions related to byte arrays.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public enum UBytes {
    
    /** The instance (short hand). */
    _,
    
    /** The instance (static import). */
    UBytes,
    
    /** The instance (proper way). */
    INSTANCE;
    
    /** The number of bytes to contains an interger. */
    public static final int BYTES_FOR_INT = 4;
    
    // == Integer array ================================================================================================
    
    /**
     * Convert array of integers to array of bytes.
     * 
     * @param intsStr
     *            the string of the array of integers.
     * @return the array integers.
     */
    public int[] stringToInts(
            final String intsStr) {
        final String stripped = intsStr.substring(1, intsStr.length() - 1);     // 2 for '[' and ']'
        final String[] eachs = stripped.split("[ \t\r\n]?,[ \t\r\n]?");
        final int length = eachs.length;
        final int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            final String each = eachs[i];
            ints[i] = Integer.parseInt(each);
        }
        return ints;
    }
    
    /**
     * Convert array of integers to array of bytes.
     * 
     * @param ints
     *            the array of integers.
     * @return the array bytes.
     */
    public byte[] intsToBytes(
            final int[] ints) {
        if (ints == null) {
            return null;
        }
        
        final byte[] bytes = new byte[ints.length];
        for (int i = 0, length = ints.length; i < length; i++) {
            final int oldI = ints[i];
            final byte b = (byte) oldI;
            bytes[i] = b;
            if ((int) b != oldI) {
                throw new IllegalArgumentException();
            }
        }
        return bytes;
    }
    
    // == Integer ======================================================================================================
    
    /**
     * Convert an integer to be a byte array.
     * 
     * @param intValue
     *            the integer value.
     * @return the byte array representing the integer.
     */
    public byte[] intToBytes(
            final int intValue) {
        final byte[] bytes = ByteBuffer.allocate(BYTES_FOR_INT).order(ByteOrder.BIG_ENDIAN).putInt(intValue).array();
        return bytes;
    }
    
    /**
     * Read an integer out of the given byte array input stream.
     * 
     * @param bytes
     *            the bytes of data.
     * @return the read integer.
     * @throws IOException
     *             when reading fail for example when there is not enough bytes to read from.
     */
    public int bytesToInt(
            final byte[] bytes)
            throws IOException {
        final ByteArrayInputStream buffer = new ByteArrayInputStream(bytes);
        final int count = bytesToInt(buffer);
        return count;
    }
    
    /**
     * Read an integer out of the given byte array input stream.
     * 
     * @param buffer
     *            the buffer.
     * @return the read integer.
     * @throws IOException
     *             when reading fail for example when there is not enough bytes to read from.
     */
    public int bytesToInt(
            final ByteArrayInputStream buffer)
            throws IOException {
        final byte[] countBytes = new byte[BYTES_FOR_INT];
        buffer.read(countBytes);
        final int count = ByteBuffer.wrap(countBytes).getInt();
        return count;
    }
    
    // == Bytes array ==================================================================================================
    
    /**
     * Convert array of bytes to bytes.
     * 
     * @param data
     *            the array of bytes.
     * @return the result bytes.
     */
    public byte[] bytesArrayToBytes(
            final byte[]... data) {
        try {
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            final int dataLength = data.length;
            final byte[] countBytes = intToBytes(dataLength);
            buffer.write(countBytes);
            
            for (final byte[] bytes : data) {
                final int length = bytes.length;
                final byte[] lengthBytes = intToBytes(length);
                buffer.write(lengthBytes);
                buffer.write(bytes);
            }
            
            return buffer.toByteArray();
            
        } catch (final IOException problem) {
            throw new ConvertToBytesException(problem);
        }
    }
    
    /**
     * Convert bytes to array of bytes .
     * 
     * @param bytes
     *            the bytes.
     * @return the array of bytes.
     */
    public byte[][] bytesToBytesArray(
            final byte[] bytes) {
        try {
            final ByteArrayInputStream buffer = new ByteArrayInputStream(bytes);
            final byte[] countBytes = new byte[BYTES_FOR_INT];
            buffer.read(countBytes);
            final int count = ByteBuffer.wrap(countBytes).getInt();
            
            final byte[][] data = new byte[count][];
            for (int i = 0; i < count; i++) {
                final byte[] lengthBytes = new byte[BYTES_FOR_INT];
                buffer.read(lengthBytes);
                final int length = ByteBuffer.wrap(lengthBytes).getInt();
                
                final byte[] eachBytes = new byte[length];
                buffer.read(eachBytes);
                
                data[i] = eachBytes;
            }
            return data;
        } catch (final IOException problem) {
            throw new ConvertFromBytesException(problem);
        }
    }
    
    // == Serializable =================================================================================================
    
    /**
     * Converts a serializable to byte array.
     * 
     * @param serializable
     *            the serializable.
     * @param <T>
     *            the desired serializable class.
     * @return the byte array.
     */
    public <T extends Serializable> byte[] serializableToBytes(
            final T serializable) {
        ByteArrayOutputStream outStream = null;
        try {
            outStream = new ByteArrayOutputStream();
            final ObjectOutputStream objStream = new ObjectOutputStream(outStream);
            objStream.writeObject(serializable);
            final byte[] bytes = outStream.toByteArray();
            return bytes;
        } catch (final IOException problem) {
            throw new ConvertToBytesException(problem);
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (final IOException problem) {
                    throw new ConvertToBytesException(problem);
                }
            }
        }
    }
    
    /**
     * Convert bytes to a serializable.
     * 
     * @param bytes
     *            the byte array.
     * @param clazz
     *            the serializable class for the data.
     * @param <T>
     *            the desired serializable class.
     * @return the serializable read from the byte array.
     */
    public <T extends Serializable> T bytesToSerializable(
            final byte[] bytes,
            final Class<T> clazz) {
        ByteArrayInputStream inStream = null;
        try {
            inStream = new ByteArrayInputStream(bytes);
            final ObjectInputStream objStream = new ObjectInputStream(inStream);
            final T data = clazz.cast(objStream.readObject());
            return data;
        } catch (final ClassNotFoundException problem) {
            throw new ConvertFromBytesException(problem);
        } catch (final IOException problem) {
            throw new ConvertFromBytesException(problem);
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (final IOException problem) {
                    throw new ConvertFromBytesException(problem);
                }
            }
        }
    }
    
    // == Serializables ================================================================================================
    
    /**
     * Convert serializables to bytes.
     * 
     * @param data
     *            the serializables.
     * @return the result bytes.
     */
    public byte[] serializablesToBytes(
            final Serializable... data) {
        final byte[] bytes = serializableToBytes(data);
        return bytes;
    }
    
    /**
     * Convert bytes to array of serializables.
     * 
     * @param bytes
     *            the bytes.
     * @return the array of serializables.
     */
    public Serializable[] bytesToSerializables(
            final byte[] bytes) {
        final Serializable[] serializables = bytesToSerializable(bytes, Serializable[].class);
        return serializables;
    }
    
    /**
     * Convert bytes to array of serializables.
     * 
     * @param bytes
     *            the bytes.
     * @param clazz
     *            the data class.
     * @param <T>
     *            the desired serializable class.
     * @return the array of serializables.
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T[] bytesToSerializables(
            final byte[] bytes,
            final Class<T> clazz) {
        final Serializable[] serializables = bytesToSerializables(bytes);
        final T[] data = (T[]) Array.newInstance(clazz, serializables.length);
        for (int i = 0; i < serializables.length; i++) {
            data[i] = (T) serializables[i];
        }
        return data;
    }
    
}
