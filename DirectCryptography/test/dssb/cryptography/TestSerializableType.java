package dssb.cryptography;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import dssb.cryptography.datatypes.ArrayOfBytesType;
import dssb.cryptography.datatypes.SerializableType;
import dssb.cryptography.datatypes.SerializablesType;

public class TestSerializableType {
    
    @Test
    public void testInt() {
        final Integer data = 10;
        final SerializableType<Integer> type = new SerializableType<Integer>(Integer.class);
        final byte[] bytes = type.toBytes(data);
        Assert.assertEquals(Integer.valueOf(10), (Integer)type.fromBytes(bytes));
    }
    
    @Test
    public void testString() {
        final String data = "String";
        final SerializableType<String> type = new SerializableType<String>(String.class);
        final byte[] bytes = type.toBytes(data);
        Assert.assertEquals("String", (String)type.fromBytes(bytes));
    }
    
    @Test
    public void testBytes() {
        final byte[] data = "String".getBytes();
        final SerializableType<byte[]> type = new SerializableType<byte[]>(byte[].class);
        final byte[] bytes = type.toBytes(data);
        Assert.assertEquals(Arrays.toString("String".getBytes()), Arrays.toString((byte[])type.fromBytes(bytes)));
    }
    
    @Test
    public void testBytesArray() {
        final byte[] data1 = ("" + (char)65).getBytes();
        final byte[] data2 = ("" + (char)66).getBytes();
        final byte[] data = ArrayOfBytesType.TYPE.toBytes(data1, data2);
        Assert.assertEquals("[0, 0, 0, 2, 0, 0, 0, 1, 65, 0, 0, 0, 1, 66]", Arrays.toString(data));
    }
    
    @Test
    public void testSerializables() {
        final Integer data1 = 10;
        final Integer data2 = 20;
        final SerializablesType<Integer> type = new SerializablesType<Integer>(Integer.class);
        final byte[] bytes = type.toBytes(data1, data2);
        System.out.println(Arrays.toString(bytes));
        System.out.println(Arrays.toString(type.fromBytes(bytes)));
        Assert.assertEquals(Arrays.toString(new Integer[] { 10, 20 }), Arrays.toString(type.fromBytes(bytes)));
    }
    
}
