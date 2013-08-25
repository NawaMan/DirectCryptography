package dssb.cryptography;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import dssb.cryptography.datatypes.SerializableType;
import dssb.cryptography.encoder.Encoder;
import dssb.cryptography.encoder.EncoderFactory;
import dssb.cryptography.schemes.base64.Base64;

public class TestBase64 {
    
    private Encoder encoder;
    
    @Before
    public void init() {
        final CryptographyBuilder builder = Base64._.createCryptographyBuilder();
        final Cryptography cryptography = builder.newCryptography();
        final EncoderFactory encoderFactory = cryptography.getFeature(EncoderFactory.class);
        this.encoder = encoderFactory.getEncoder();
    }
    
    @Test
    public void testWithBytes() {
        final byte[] bytes = "Hello world. Hello world. Hello world. Hello world. ".getBytes();
        Assert.assertEquals("SGVsbG8gd29ybGQuIEhlbGxvIHdvcmxkLiBIZWxsbyB3b3JsZC4gSGVsbG8gd29ybGQuIA==", encoder.encode(bytes));
    }
    
    @Test
    public void testWithData() {
        final SerializableType<String> type = new SerializableType<String>(String.class);
        final String str = "Hello world. Hello world. Hello world. Hello world. ";
        final Data<String> data = type.toData(str);
        Assert.assertEquals(
                "rO0ABXQANEhlbGxvIHdvcmxkLiBIZWxsbyB3b3JsZC4gSGVsbG8gd29ybGQuIEhlbGxvIHdvcmxk\n"
              + "LiA=", encoder.encode(data));
    }
    
    // TODO - Add decoder.
}
