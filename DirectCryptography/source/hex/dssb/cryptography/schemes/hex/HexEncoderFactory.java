package dssb.cryptography.schemes.hex;

import java.util.Formatter;

import dssb.cryptography.encoder.Encoder;
import dssb.cryptography.encoder.EncoderFactory;

/**
 * Hasher factory for message digest.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class HexEncoderFactory extends EncoderFactory.Simple {
    
    /**
     * Constructor.
     * 
     * @param cryptography
     *            the cryptography;
     */
    public HexEncoderFactory(
            final HexCryptography cryptography) {
        super(cryptography);
    }
    
    /** {@inheritDoc} */
    @Override
    public HexCryptography getCryptography() {
        return (HexCryptography) super.getCryptography();
    }
    
    @Override
    public Encoder newEncoder() {
        return new Encoder() {
            
            @Override
            public EncoderFactory getEncoderFactory() {
                return HexEncoderFactory.this;
            }
            
            @Override
            public String encode(
                    final byte[] data) {
                return HexEncoderFactory.this.encode(data);
            }
            
            @Override
            public byte[] decode(
                    final String encodedString) {
                return HexEncoderFactory.this.decode(encodedString);
            }
        };
    }
    
    public String encode(
            final byte[] hash) {
        final int byteForColumn = this.getCryptography().getBytePerColumn();
        Formatter formatter = new Formatter();
        int i = 0;
        for (byte b : hash) {
            formatter.format("%02x", b);
            if (byteForColumn >= 1) {
                if ((i % 4) == 3)
                    formatter.format(" ");
                i++;
            }
        }
        String hex = formatter.toString();
        formatter.close();
        return hex;
    }
    
    @Override
    public byte[] decode(
            String base64) {
        throw new UnsupportedOperationException("Later");
    }
    
}
