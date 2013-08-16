package dssb.cryptography.datatypes;

import java.nio.charset.Charset;

/**
 * The text type.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class TextType extends dssb.cryptography.Type.Simple<String> {
    
    /** The charset for UTF8. */
    static public final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    
    /** The separater between the charset name and the text. */
    static public final String SEPARATOR = "\n";
    
    /** The type instance. */
    public static TextType TYPE = new TextType();
    
    /** The type instance for UTF8. */
    public static TextType UTF8 = new TextType(TextType.UTF8_CHARSET);
    
    /** The char set. */
    private final Charset charset;
    
    /**
     * Constructs a text type with UTF8 charset.
     */
    public TextType() {
        this(null);
    }
    
    /**
     * Constructs this text type with charset.
     * 
     * @param charset
     *            the charset or UTF8 if {@code null} is passed on.
     */
    public TextType(
            final Charset charset) {
        this.charset = (charset == null)
                ? UTF8_CHARSET
                : charset;
    }
    
    @Override
    public Class<String> getDataClass() {
        return String.class;
    }
    
    @Override
    public byte[] toBytes(
            final String data) {
        final byte[] charSetBytes = (this.charset.name()).getBytes(UTF8_CHARSET);
        final byte[] dataBytes = data.getBytes(this.charset);
        final byte[] bytes = ArrayOfBytesType.TYPE.toBytes(charSetBytes, dataBytes);
        return bytes;
    }
    
    @Override
    public String fromBytes(
            final byte[] bytes) {
        final byte[][] readBytes = ArrayOfBytesType.TYPE.fromBytes(bytes);
        final byte[] charSetBytes = readBytes[0];
        final byte[] dataBytes = readBytes[1];
        final String charsetName = new String(charSetBytes, UTF8_CHARSET);
        final Charset charSet = Charset.forName(charsetName);
        final String data = new String(dataBytes, charSet);
        return data;
    }
    
    @Override
    public Data toData(
            final String data) {
        return new Data(data);
    }
    
    /**
     * The data fror TypeType.
     */
    public static class Data extends dssb.cryptography.Data.Simple<String> {
        
        /**
         * Constructs the data with a stirng.
         * 
         * @param data
         *            the string.
         */
        public Data(
                final String data) {
            super(TYPE, data);
        }
        
        /**
         * Constructs the data with a stirng and charset.
         * 
         * @param charset
         *            the character set.
         * @param data
         *            the string data.
         */
        public Data(
                final Charset charset,
                final String data) {
            super(new TextType(charset), data);
        }
        
        /**
         * Constructs the data with the type type..
         * 
         * @param type
         *            the text type.
         * @param data
         *            the string data.
         */
        public Data(
                final TextType type,
                final String data) {
            super(type, data);
        }
        
        /** {@inheritDoc} */
        @Override
        public String toString() {
            return this.getData();
        }
        
    }
    
}
