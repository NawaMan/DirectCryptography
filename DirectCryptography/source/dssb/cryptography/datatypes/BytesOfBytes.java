package dssb.cryptography.datatypes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import dssb.cryptography.ConvertFromBytesException;
import dssb.cryptography.ConvertToBytesException;
import dssb.cryptography.Data;

public class BytesOfBytes extends Data.Simple<byte[][]> {
    
    static public class Type extends dssb.cryptography.Type.Simple<byte[][]> {
        
        @Override
        public Class<byte[][]> getDataClass() {
            return byte[][].class;
        }
        
        @Override
        public byte[] toBytes(
                final byte[] ... data) {
            try {
                final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                final byte[] countBytes = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(data.length).array();
                buffer.write(countBytes);
                
                for (final byte[] bytes : data) {
                    final byte[] lengthBytes = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(bytes.length).array();
                    buffer.write(lengthBytes);
                    buffer.write(bytes);
                }
                
                return buffer.toByteArray();
                
            } catch (final IOException problem) {
                throw new ConvertToBytesException(problem);
            }
        }
        
        @Override
        public byte[][] fromBytes(
                byte[] bytes) {
            try {
                final ByteArrayInputStream buffer = new ByteArrayInputStream(bytes);
                final byte[] countBytes = new byte[4];
                buffer.read(countBytes);
                final int count = ByteBuffer.wrap(countBytes).getInt();
                
                final byte[][] data = new byte[count][];
                for (int i = 0; i < count; i++) {
                    final byte[] lengthBytes = new byte[4];
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
        
        @Override
        public BytesOfBytes toData(
                final byte[][] data) {
            return new BytesOfBytes(data);
        }
        
    }
    
    public static BytesOfBytes.Type TYPE = new Type();
    
    public BytesOfBytes(
            final byte[][] data) {
        super(TYPE, data);
    }
    
}
