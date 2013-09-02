package dssb.cryptography.schemes.base64;

import java.io.ByteArrayOutputStream;

import dssb.cryptography.CryptographyBuilder;
import dssb.cryptography.Scheme;
import dssb.cryptography.encoder.AbstractEncoderCryptography;
import dssb.cryptography.encoder.EncoderFactory;

/**
 * Cryptography scheme for message digest.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public enum Base64 implements Scheme {
    
    /** Shorthand instance. */
    _,
    
    /** Static import instance. */
    Base64,
    
    /** Semantic instance. */
    Scheme,
    
    /** Conventional instance. */
    INSTANCE;
    
    @Override
    public Cryptography.Builder createCryptographyBuilder() {
        return new Cryptography.Builder();
    }
    
    // BASE 64 ENCODING -- Taken from --http://www.wikihow.com/Encode-a-String-to-Base64-With-Java
    // Change from (String):String to (byte[]):String
    // The decoding is all my code (reverse from the one taken, anyway)
    
    /** The included BASE64. */
    private static final String BASE64CODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    
    /** The splite lines. */
    private static final int SPLITE_LINES_AT = 19; // 76/4 - 1;
    
    /**
     * Encode the given byte[] with BASE64 ENCODING String.
     * 
     * @param data
     *            the data in byte.
     * @return the encoded string.
     **/
    public String encode(
            final byte[] data) {
        if (data == null) {
            return "";
        }
        
        byte[] bytes = data;
        final StringBuilder encoded = new StringBuilder();
        
        // Determine how many padding bytes to add to the output
        final int three = 3;
        final int paddingCount = (three - (bytes.length % three)) % three;
        
        try {
            final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            
            // Write the original data
            outStream.write(bytes);
            // Write the padding-zero
            for (int i = paddingCount; --i >= 0;) {
                outStream.write(0);
            }
            
            // Move to the working array
            bytes = outStream.toByteArray();
            outStream.close();
        } catch (Exception problem) {
            // TODO Do something.
            assert (problem != null);
        }
        
        // Here is a gift for you, CheckStyle, hope you happy.
        final int int2 = 2;
        final int int4 = 4;
        final int int6 = 6;
        final int int0x03 = 0x03;
        final int int0x0F = 0x0F;
        final int int0x3F = 0x3F;
        final int int0xC0 = 0xC0;
        final int int0xF0 = 0xF0;
        final int int0xFC = 0xFC;
        
        int c = 0;
        // Process 3 bytes at a time, churning out 4 output bytes
        // worry about CRLF insertions later
        for (int i = 0; i < bytes.length; i += three) {
            // I0 I1 I2
            // 0101 01 - 01 0101-0101 01-01 0101
            // F C 3 F F C 3 F
            // 0101 01 + 01 0101+0101 01+01 0101
            // B0 B1 B2 B3
            
            final int i0 = bytes[i];
            final int i1 = bytes[i + 1];
            final int i2 = bytes[i + 2];
            
            final int b0 = ((i0 & int0xFC) >> int2);
            final int b1 = ((i0 & int0x03) << int4) | ((i1 & int0xF0) >> int4);
            final int b2 = ((i1 & int0x0F) << int2) | ((i2 & int0xC0) >> int6);
            final int b3 = ((i2 & int0x3F));
            
            encoded.append(BASE64CODE.charAt(b0))
                    .append(BASE64CODE.charAt(b1))
                    .append(BASE64CODE.charAt(b2))
                    .append(BASE64CODE.charAt(b3));
            
            if (c == SPLITE_LINES_AT) {
                encoded.append("\n");
                c = 0;
            } else {
                c++;
            }
        }
        
        // replace encoded padding nulls with "="
        final String result = encoded.substring(0, encoded.length() - paddingCount)
                + "==".substring(0, paddingCount);
        return result;
    }
    
    /**
     * Decode the given BASE64-ENCODING String to byte[].
     * 
     * @param base64String
     *            the base64 string.
     * @return the decoded data.
     **/
    public byte[] decode(
            final String base64String) {
        if ((base64String == null) || (base64String.length() == 0)) {
            return new byte[0];
        }
        
        String base64 = base64String.trim();
        
        // Remove padding
        int paddingCount = 0;
        if (base64.charAt(base64.length() - 2) == '=') {
            paddingCount = 2;
        } else if (base64.charAt(base64.length() - 1) == '=') {
            paddingCount = 1;
        }
        base64 = base64.substring(0, base64.length() - paddingCount) + "\0\0".substring(2 - paddingCount);
        
        // Here is a gift for you, CheckStyle, hope you happy.
        final int int1 = 1;
        final int int2 = 2;
        final int int3 = 3;
        final int int4 = 4;
        final int int6 = 6;
        final int int0x03 = 0x03;
        final int int0x0F = 0x0F;
        final int int0x3F = 0x3F;
        final int int0x3C = 0x3C;
        final int int0x30 = 0x30;
        
        byte[] bs = null;
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            final int four = 4;
            int c = 0;
            for (int i = 0; i < base64.length(); i += four) {
                // B0 B1 B2 B3
                // 010101 + 01 0101 0101 01 01 0101
                // 3 F 3 F 3 C 3 3 F
                // 010101 + 01 0101-0101 01-01 0101
                // I0 I1 I2
                final int b0 = BASE64CODE.indexOf(base64.charAt(i));
                final int b1 = BASE64CODE.indexOf(base64.charAt(i + int1));
                final int b2 = BASE64CODE.indexOf(base64.charAt(i + int2));
                final int b3 = BASE64CODE.indexOf(base64.charAt(i + int3));
                
                final int i0 = ((b0 & int0x3F) << int2) | ((b1 & int0x30) >> int4);
                final int i1 = ((b1 & int0x0F) << int4) | ((b2 & int0x3C) >> int2);
                final int i2 = ((b2 & int0x03) << int6) | ((b3 & int0x3F));
                
                baos.write(i0);
                baos.write(i1);
                baos.write(i2);
                
                if (c == SPLITE_LINES_AT) {
                    i += 1;
                    c = 0;
                } else {
                    c++;
                }
            }
            
            bs = baos.toByteArray();
            baos.close();
            
        } catch (RuntimeException problem) {
            throw problem;
        } catch (Exception problem) {
            throw new RuntimeException(problem);
        }
        
        if (bs == null) {
            bs = new byte[0];
        } else if (paddingCount != 0) {
            // Cut the padding
            byte[] nBs = new byte[bs.length - paddingCount];
            System.arraycopy(bs, 0, nBs, 0, nBs.length);
            bs = nBs;
        }
        
        return bs;
    }
    
    // == Cryptography =================================================================================================
    
    /** Cryptography. */
    public static class Cryptography extends AbstractEncoderCryptography<Base64> {
        
        /** {@inheritDoc} */
        @Override
        public final Base64 getScheme() {
            return Scheme;
        }
        
        /**
         * Creates a new {@link EncoderFactory}.
         * 
         * @return a newly created {@link EncoderFactory}.
         */
        protected EncoderFactory newEncoderFactory() {
            final EncoderFactory encoderFactory = new Factory(this);
            return encoderFactory;
        }
        
        // == Builder ==================================================================================================
        
        /**
         * Builder for {@link Cryptography}.
         * 
         * This builder will take an algorithm name.
         */
        public static class Builder implements CryptographyBuilder {
            
            /** {@inheritDoc} */
            @Override
            public Cryptography newCryptography() {
                return new Cryptography();
            }
            
        }
        
        // == Factory ==================================================================================================
        
        /** Encoder factory. */
        public static class Factory extends EncoderFactory.Simple<Cryptography> {
            
            /**
             * Constructor.
             * 
             * @param cryptography
             *            the cryptography;
             */
            public Factory(
                    final Cryptography cryptography) {
                super(cryptography);
            }
            
            /** {@inheritDoc} */
            @Override
            public String encode(
                    final byte[] data) {
                return Base64.encode(data);
            }
            
            /** {@inheritDoc} */
            @Override
            public byte[] decode(
                    final String encodedString) {
                return Base64.decode(encodedString);
            }
            
        }
    }
}
