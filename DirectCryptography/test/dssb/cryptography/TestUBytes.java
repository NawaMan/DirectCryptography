package dssb.cryptography;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import dssb.cryptography.datatypes.ArrayOfBytesType;

public class TestUBytes {
    
    /** Try to used it to store a bytes array. */
    @Test
    public void testBytesArray() {
        final byte a = 'A';
        final byte b = 'B';
        final byte[] data1 = ("" + a).getBytes();
        final byte[] data2 = ("" + b).getBytes();
        final byte[] data = ArrayOfBytesType.TYPE.toBytes(data1, data2);
        Assert.assertEquals("[0, 0, 0, 2, 0, 0, 0, 1, 65, 0, 0, 0, 1, 66]", Arrays.toString(data));     // TODO Tests in this test case should be end-to-end ... the result byte array should be tested where else.
    }
    
}
