package dssb.cryptography.datatypes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

import dssb.cryptography.ConvertFromBytesException;
import dssb.cryptography.ConvertToBytesException;
import dssb.cryptography.Data;
import dssb.cryptography.DataType;

public class SerializeType<_Type_ extends Serializable> extends DataType.Simple<_Type_> {
    
    private final Class<_Type_> type;
    
    public SerializeType(
            final Class<_Type_> type) {
        if (type == null) {
            throw new NullPointerException();
        }
        this.type = type;
    }
    
    @Override
    public Class<_Type_> getDataClass() {
        return this.type;
    }
    
    @Override
    public byte[] toBytes(
            final _Type_ data) {
        ByteArrayOutputStream outStream = null;
        try {
            outStream = new ByteArrayOutputStream();
            final ObjectOutputStream objStream = new ObjectOutputStream(outStream);
            objStream.writeObject(data);
            final byte[] bytes = outStream.toByteArray();
            return bytes;
        } catch (final IOException problem) {
            throw new ConvertToBytesException(problem);
        } finally {
            try {
                outStream.close();
            } catch (final IOException problem) {
                throw new ConvertToBytesException(problem);
            }
        }
    }
    
    @Override
    public _Type_ fromBytes(
            final byte[] bytes) {
        ByteArrayInputStream inStream = null;
        try {
            inStream = new ByteArrayInputStream(bytes);
            final ObjectInputStream objStream = new ObjectInputStream(inStream);
            final _Type_ data = this.type.cast(objStream.readObject());
            return data;
        } catch (final ClassNotFoundException problem) {
            throw new ConvertFromBytesException(problem);
        } catch (final IOException problem) {
            throw new ConvertFromBytesException(problem);
        } finally {
            try {
                inStream.close();
            } catch (final IOException problem) {
                throw new ConvertFromBytesException(problem);
            }
        }
    }
    
    @Override
    public Data<_Type_> toClearData(
            _Type_ data) {
        return new Data.Simple<_Type_>(this, data);
    }
    
    static public void main(
            final String... args) {
        final byte[] data = "Some text.".getBytes();
        final Data<byte[]> clearData = new Data.Simple<byte[]>(new SerializeType<byte[]>(byte[].class), data);
        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(clearData.getData()));
        System.out.println(clearData.getDataType());
        final byte[] bytes = clearData.toBytes();
        System.out.println(Arrays.toString(clearData.getDataType().fromBytes(bytes)));
    }
    
}