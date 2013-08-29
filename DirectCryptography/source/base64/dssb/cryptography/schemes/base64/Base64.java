package dssb.cryptography.schemes.base64;

import java.io.ByteArrayOutputStream;

import dssb.cryptography.CryptographyBuilder;
import dssb.cryptography.Scheme;
import dssb.cryptography.encoder.AbstractEncoderCryptography;
import dssb.cryptography.encoder.EncoderFactory;
import dssb.cryptography.hasher.HasherFactory;

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
    public static String BASE64CODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    
    /** The splite lines */
    public static int SPLITE_LINES_AT = 19 - 1; // 76/4 - 1;
    
    /**
     * Encode the given byte[] with BASE64 ENCODING String
     * 
     * @param data
     *            the data in byte.
     * @return the encoded string.
     **/
    public String encode(
            final byte[] data) {
        if (data == null)
            return "";
        
        byte[] bytes = data;
        final StringBuilder encoded = new StringBuilder();
        
        // Determine how many padding bytes to add to the output
        final int paddingCount = (3 - (bytes.length % 3)) % 3;
        
        try {
            final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            
            // Write the original data
            outStream.write(bytes);
            // Write the padding-zero
            for (int i = paddingCount; --i >= 0;)
                outStream.write(0);
            
            // Move to the working array
            bytes = outStream.toByteArray();
            outStream.close();
        } catch (Exception E) {
        }
        
        int c = 0;
        // Process 3 bytes at a time, churning out 4 output bytes
        // worry about CRLF insertions later
        for (int i = 0; i < bytes.length; i += 3) {
            // I0 I1 I2
            // 0101 01 - 01 0101-0101 01-01 0101
            // F C 3 F F C 3 F
            // 0101 01 + 01 0101+0101 01+01 0101
            // B0 B1 B2 B3
            
            final int i0 = bytes[i];
            final int i1 = bytes[i + 1];
            final int i2 = bytes[i + 2];
            
            final int b0 = ((i0 & 0xFC) >> 2);
            final int b1 = ((i0 & 0x03) << 4) | ((i1 & 0xF0) >> 4);
            final int b2 = ((i1 & 0x0F) << 2) | ((i2 & 0xC0) >> 6);
            final int b3 = (i2 & 0x3F);
            
            encoded.append(BASE64CODE.charAt(b0))
                    .append(BASE64CODE.charAt(b1))
                    .append(BASE64CODE.charAt(b2))
                    .append(BASE64CODE.charAt(b3));
            
            if (c == SPLITE_LINES_AT) {
                encoded.append("\n");
                c = 0;
            } else
                c++;
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
        if ((base64String == null) || (base64String.length() == 0))
            return new byte[0];
        
        String base64 = base64String.trim();
        
        // Remove padding
        int PaddingCount = 0;
        if (base64.charAt(base64.length() - 2) == '=')
            PaddingCount = 2;
        else if (base64.charAt(base64.length() - 1) == '=')
            PaddingCount = 1;
        base64 = base64.substring(0, base64.length() - PaddingCount) + "\0\0".substring(2 - PaddingCount);
        
        byte[] Bs = null;
        try {
            ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
            
            int c = 0;
            for (int i = 0; i < base64.length(); i += 4) {
                // B0 B1 B2 B3
                // 010101 + 01 0101 0101 01 01 0101
                // 3 F 3 F 3 C 3 3 F
                // 010101 + 01 0101-0101 01-01 0101
                // I0 I1 I2
                
                final int B0 = BASE64CODE.indexOf(base64.charAt(i));
                final int B1 = BASE64CODE.indexOf(base64.charAt(i + 1));
                int B2 = BASE64CODE.indexOf(base64.charAt(i + 2));
                int B3 = BASE64CODE.indexOf(base64.charAt(i + 3));
                
                int I0 = ((B0 & 0x3F) << 2) | ((B1 & 0x30) >> 4);
                int I1 = ((B1 & 0x0F) << 4) | ((B2 & 0x3C) >> 2);
                int I2 = ((B2 & 0x03) << 6) | ((B3 & 0x3F));
                
                BAOS.write(I0);
                BAOS.write(I1);
                BAOS.write(I2);
                
                if (c == SPLITE_LINES_AT) {
                    i += 1;
                    c = 0;
                } else
                    c++;
            }
            
            Bs = BAOS.toByteArray();
            BAOS.close();
            
        } catch (RuntimeException E) {
            throw E;
        } catch (Exception E) {
            throw new RuntimeException(E);
        }
        
        if (Bs == null)
            Bs = new byte[0];
        else if (PaddingCount != 0) {
            // Cut the padding
            byte[] NBs = new byte[Bs.length - PaddingCount];
            System.arraycopy(Bs, 0, NBs, 0, NBs.length);
            Bs = NBs;
        }
        
        return Bs;
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
         * Creates a new {@link HasherFactory}.
         * 
         * @return a newly created {@link HasherFactory}.
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
