package dssb.cryptography;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import dssb.cryptography.util.UBytes;

/**
 * Test for {@code UBytes}.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class TestUBytes {
    
    // == Integer ======================================================================================================
    
    /** Convert a integer to bytes. */
    @Test
    public void testIntegerToBytes() {
        final int i = 123456;
        final byte[] data = UBytes._.intToBytes(i);
        Assert.assertEquals("[0, 1, -30, 64]", Arrays.toString(data));
    }
    
    /**
     * Convert bytes from an integer.
     * 
     * @throws IOException
     *             PROBLEM!!!
     **/
    @Test
    public void testBytesToInteger()
            throws IOException {
        final byte[] bytes = { 0, 1, -30, 64 };
        final int i = UBytes._.bytesToInt(bytes);
        final int iExpected = 123456;
        Assert.assertEquals(iExpected, i);
    }
    
    /**
     * Convert to and from an integer.
     * 
     * @throws IOException
     *             PROBLEM!!!
     **/
    @Test
    public void testInteger()
            throws IOException {
        final int iIn = 123456;
        final byte[] data = UBytes._.intToBytes(iIn);
        final int iOut = UBytes._.bytesToInt(data);
        Assert.assertEquals(iIn, iOut);
    }
    
    // == Bytes array ==================================================================================================
    
    /** Convert bytes array to bytes. */
    @Test
    public void testToBytesArray() {
        final byte a = 'A';
        final byte b = 'B';
        final byte[] data1 = ("" + (char) a).getBytes();
        final byte[] data2 = ("" + (char) b).getBytes();
        final byte[] data = UBytes._.bytesArrayToBytes(data1, data2);
        Assert.assertEquals("[0, 0, 0, 2, 0, 0, 0, 1, 65, 0, 0, 0, 1, 66]", Arrays.toString(data));
    }
    
    /** Convert bytes from bytes array. */
    @Test
    public void testFromBytesArray() {
        final byte[] bytes = { 0, 0, 0, 2, 0, 0, 0, 1, 65, 0, 0, 0, 1, 66 };
        final byte[][] datas = UBytes._.bytesToBytesArray(bytes);
        final char a = new String(datas[0]).charAt(0);
        final char b = new String(datas[1]).charAt(0);
        Assert.assertEquals('A', a);
        Assert.assertEquals('B', b);
    }
    
    /** Convert to and from bytes array. */
    @Test
    public void testBytesArray() {
        final byte aIn = 'A';
        final byte bIn = 'B';
        final byte[] data1 = ("" + (char) aIn).getBytes();
        final byte[] data2 = ("" + (char) bIn).getBytes();
        final byte[] data = UBytes._.bytesArrayToBytes(data1, data2);
        final byte[][] datas = UBytes._.bytesToBytesArray(data);
        final char aOut = new String(datas[0]).charAt(0);
        final char bOut = new String(datas[1]).charAt(0);
        Assert.assertEquals(aIn, aOut);
        Assert.assertEquals(bIn, bOut);
    }
    
    // == Serializable =================================================================================================
    
    /** Convert a serializable to bytes. */
    @Test
    public void testSerializableToBytes() {
        final String str = "String";
        final byte[] data = UBytes._.serializableToBytes(str);
        Assert.assertEquals("[-84, -19, 0, 5, 116, 0, 6, 83, 116, 114, 105, 110, 103]", Arrays.toString(data));
    }
    
    /** Convert bytes from a serializable. */
    @Test
    public void testBytesToSerializable() {
        final byte[] bytes = { -84, -19, 0, 5, 116, 0, 6, 83, 116, 114, 105, 110, 103 };
        final String str = UBytes._.bytesToSerializable(bytes, String.class);
        Assert.assertEquals("String", str);
    }
    
    /** Convert to and from a serializable. */
    @Test
    public void testSerializable() {
        final String strIn = "String";
        final byte[] data = UBytes._.serializableToBytes(strIn);
        final String strOut = UBytes._.bytesToSerializable(data, String.class);
        Assert.assertEquals(strIn, strOut);
    }
    
    // == Serializables ================================================================================================
    
    /** Convert a serializables to bytes. */
    @Test
    public void testSerializablesToBytes() {
        final String str1 = "String1";
        final String str2 = "String2";
        final byte[] data = UBytes._.serializablesToBytes(str1, str2);
        Assert.assertEquals(
                "[-84, -19, 0, 5, 117, 114, 0, 23, 91, 76, 106, 97, 118, 97, 46, 105, 111, 46, 83, 101, 114, 105, "
                        + "97, 108, 105, 122, 97, 98, 108, 101, 59, -82, -48, 9, -84, 83, -41, -19, 73, 2, 0, 0, 120,"
                        + " 112, 0, 0, 0, 2, 116, 0, 7, 83, 116, 114, 105, 110, 103, 49, 116, 0, 7, 83, 116, 114, "
                        + "105, 110, 103, 50]",
                Arrays.toString(data));
    }
    
    /** Convert bytes from a serializables. */
    @Test
    public void testBytesToSerializables() {
        final byte[] bytes = { (byte) -84, (byte) -19, (byte) 0, (byte) 5, (byte) 117, (byte) 114, (byte) 0, (byte) 23,
            (byte) 91, (byte) 76, (byte) 106, (byte) 97, (byte) 118, (byte) 97, (byte) 46, (byte) 105, (byte) 111,
            (byte) 46, (byte) 83, (byte) 101, (byte) 114, (byte) 105, (byte) 97, (byte) 108, (byte) 105, (byte) 122,
            (byte) 97, (byte) 98, (byte) 108, (byte) 101, (byte) 59, (byte) -82, (byte) -48, (byte) 9, (byte) -84,
            (byte) 83, (byte) -41, (byte) -19, (byte) 73, (byte) 2, (byte) 0, (byte) 0, (byte) 120, (byte) 112,
            (byte) 0, (byte) 0, (byte) 0, (byte) 2, (byte) 116, (byte) 0, (byte) 7, (byte) 83, (byte) 116,
            (byte) 114, (byte) 105, (byte) 110, (byte) 103, (byte) 49, (byte) 116, (byte) 0, (byte) 7, (byte) 83,
            (byte) 116, (byte) 114, (byte) 105, (byte) 110, (byte) 103, (byte) 50 };
        final String[] strs = UBytes._.bytesToSerializables(bytes, String.class);
        Assert.assertEquals("String1", strs[0]);
        Assert.assertEquals("String2", strs[1]);
    }
    
    /** Convert bytes to and from a serializables. */
    @Test
    public void testSerializables() {
        final String str1 = "String1";
        final String str2 = "String2";
        final byte[] data = UBytes._.serializablesToBytes(str1, str2);
        final String[] strs = UBytes._.bytesToSerializables(data, String.class);
        Assert.assertEquals(str1, strs[0]);
        Assert.assertEquals(str2, strs[1]);
    }
    
}
