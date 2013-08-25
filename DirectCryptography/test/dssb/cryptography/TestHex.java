package dssb.cryptography;

import junit.framework.Assert;

import org.junit.Test;

import dssb.cryptography.encoder.Encoder;
import dssb.cryptography.encoder.EncoderFactory;
import dssb.cryptography.schemes.hex.Hex;

public class TestHex {

    @Test
    public void testFourBytesPerColumn() {
        final Hex.Cryptography.Builder builder = Hex.Scheme.createCryptographyBuilder();
        builder.setBytePerColumn(4);
        final Cryptography cryptography = builder.newCryptography();
        final EncoderFactory encoderFactory = cryptography.getFeature(EncoderFactory.class);
        final Encoder encoder = encoderFactory.getEncoder();
        final byte[] bytes = "Hello world. Hello world. Hello world. Hello world. ".getBytes();
        Assert.assertEquals("48656c6c 6f20776f 726c642e 2048656c 6c6f2077 6f726c64 2e204865 6c6c6f20 776f726c 642e2048 656c6c6f 20776f72 6c642e20 ", encoder.encode(bytes));
    }
    
    @Test
    public void testEightBytesPerColumn() {
        final Hex.Cryptography.Builder builder = Hex._.createCryptographyBuilder();
        builder.setBytePerColumn(8);
        final Cryptography cryptography = builder.newCryptography();
        final EncoderFactory encoderFactory = cryptography.getFeature(EncoderFactory.class);
        final Encoder encoder = encoderFactory.getEncoder();
        final byte[] bytes = "Hello world. Hello world. Hello world. Hello world. ".getBytes();
        Assert.assertEquals("48656c6c6f20776f 726c642e2048656c 6c6f20776f726c64 2e2048656c6c6f20 776f726c642e2048 656c6c6f20776f72 6c642e20", encoder.encode(bytes));
    }
    
    // TODO - Add decoder.
}
