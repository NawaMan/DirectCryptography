package dssb.cryptography.datatypes;

import java.nio.charset.Charset;
import java.util.Arrays;

import dssb.cryptography.Data;

public class Text extends Data.Simple<String> {
    
    static public class Type extends dssb.cryptography.Type.Simple<String> {
        
        static public final Charset UTF8_CHARSET = Charset.forName("UTF-8");
        
        static public final String SEPARATOR = "\n";
        
        private final Charset charset;
        
        public Type() {
            this(null);
        }
        
        public Type(final Charset charset) {
            this.charset = (charset == null) ? UTF8_CHARSET : charset;
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
            final byte[] bytes = BytesOfBytes.TYPE.toBytes(charSetBytes, dataBytes);
            return bytes;
        }
        
        @Override
        public String fromBytes(
                byte[] bytes) {
            final byte[][] readBytes = BytesOfBytes.TYPE.fromBytes(bytes);
            final byte[] charSetBytes = readBytes[0];
            final byte[] dataBytes = readBytes[1];
            final String charsetName = new String(charSetBytes, UTF8_CHARSET);
            final Charset charset = Charset.forName(charsetName);
            final String data = new String(dataBytes, charset);
            return data;
        }
        
        @Override
        public Text toClearData(
                final String data) {
            return new Text(data);
        }
        
    }
    
    public static Text.Type TYPE = new Type();
    
    public static Text.Type UTF8 = new Type(Type.UTF8_CHARSET);
    
    public Text(
            final String data) {
        super(TYPE, data);
    }
    
    public Text(
            final Charset charset,
            final String data) {
        super(new Type(charset), data);
    }
    
    public Text(
            final Text.Type type,
            final String data) {
        super(type, data);
    }
    
    public String toString() {
        return this.getData();
    }
    
    static public void main(final String ... args) {
        final String data = "Some text.";
        final Text text = new Text(data);
        System.out.println(data);
        System.out.println(text.getData());
        System.out.println(text.getType());
        final byte[] bytes = text.getType().toBytes(data);
        System.out.println(Arrays.toString(bytes));
        System.out.println(text.getType().fromBytes(bytes));
    }
    
}