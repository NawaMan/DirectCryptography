package dssb.cryptography.schemes.hex;

import java.util.Formatter;

import dssb.cryptography.CryptographyBuilder;
import dssb.cryptography.Scheme;
import dssb.cryptography.encoder.AbstractEncoderCryptography;
import dssb.cryptography.encoder.EncoderFactory;

/**
 * Cryptography scheme for message digest.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public enum Hex implements Scheme {
    
    /** Shorthand instance. */
    _,
    
    /** Static import instance. */
    Hex,
    
    /** Semantic instance. */
    Scheme,
    
    /** Conventional instance. */
    INSTANCE;
    
    @Override
    public Cryptography.Builder createCryptographyBuilder() {
        return new Cryptography.Builder();
    }
    
    /**
     * Encoding the given data.
     * 
     * @param byteForColumn
     *            the number of data byte for display column.
     * @param data
     *            the data.
     * @return the encoded string.
     **/
    public String encode(
            final int byteForColumn,
            final byte[] data) {
        final int devider = byteForColumn - 1;
        Formatter formatter = new Formatter();
        int i = 0;
        for (byte b : data) {
            formatter.format("%02x", b);
            if (byteForColumn > 1) {
                if ((i % byteForColumn) == devider) {
                    formatter.format(" ");
                }
                i++;
            }
        }
        String hex = formatter.toString();
        formatter.close();
        return hex;
    }
    
    /**
     * Decode the given string back to data.
     * 
     * @param byteForColumn
     *            the number of data byte for display column.
     * @param encodedString
     *            the encodes string.
     * @return the encoded string.
     **/
    public byte[] decode(
            final int byteForColumn,
            final String encodedString) {
        throw new UnsupportedOperationException("Later");
    }
    
    // == Cryptography =================================================================================================
    
    /** Cryptography for Hex. */
    public static class Cryptography extends AbstractEncoderCryptography<Hex> {
        
        /** The number of byte to be display per column. */
        private final int bytePerColumn;
        
        /** Default constructor. */
        public Cryptography() {
            this(-1);
        }
        
        /**
         * Constructor.
         * 
         * @param bytePerColumn
         *            the number of byte for each column display or negative value in case of no column.
         **/
        public Cryptography(
                final int bytePerColumn) {
            this.bytePerColumn = bytePerColumn;
        }
        
        /**
         * Return the number of byte to be display per column.
         * 
         * @return the number of byte to be display per column.
         */
        public int getBytePerColumn() {
            return this.bytePerColumn;
        }
        
        /** {@inheritDoc} */
        @Override
        public final Hex getScheme() {
            return Scheme;
        }
        
        /** {@inheritDoc} */
        @Override
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
            
            /** The number of byte to be display per column. */
            private int bytePerColumn = -1;
            
            /**
             * Change the number of byte per displayed column.
             * 
             * @param bytePerColumn
             *            the number of byte to be display per column.
             * @return the cryptography builder.
             */
            public Builder setBytePerColumn(
                    final int bytePerColumn) {
                this.bytePerColumn = bytePerColumn;
                return this;
            }
            
            /**
             * Return the number of byte to be display per column.
             * 
             * @return the number of byte to be display per column.
             */
            public int getBytePerColumn() {
                return this.bytePerColumn;
            }
            
            /** {@inheritDoc} */
            @Override
            public Cryptography newCryptography() {
                return new Cryptography(bytePerColumn);
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
                final int byteForColumn = this.getCryptography().getBytePerColumn();
                return Hex.encode(byteForColumn, data);
            }
            
            /** {@inheritDoc} */
            @Override
            public byte[] decode(
                    final String encodedString) {
                final int byteForColumn = this.getCryptography().getBytePerColumn();
                return Hex.decode(byteForColumn, encodedString);
            }
            
        }
        
    }
    
}
