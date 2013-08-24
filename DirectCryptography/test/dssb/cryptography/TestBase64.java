package dssb.cryptography;

import junit.framework.Assert;

import org.junit.Test;

import dssb.cryptography.encoder.Encoder;
import dssb.cryptography.encoder.EncoderFactory;
import dssb.cryptography.schemes.base64.Base64Scheme;

public class TestBase64 {

    @Test
    public void test() {
        final CryptographyBuilder builder = Base64Scheme._.createCryptographyBuilder();
        final Cryptography cryptography = builder.newCryptography();
        final EncoderFactory encoderFactory = cryptography.getFeature(EncoderFactory.class);
        final Encoder encoder = encoderFactory.getEncoder();
        final byte[] bytes = "Hello world. Hello world. Hello world. Hello world. ".getBytes();
        Assert.assertEquals("SGVsbG8gd29ybGQuIEhlbGxvIHdvcmxkLiBIZWxsbyB3b3JsZC4gSGVsbG8gd29ybGQuIA==", encoder.encode(bytes));
    }
    
    // TODO - Add decoder.
}
