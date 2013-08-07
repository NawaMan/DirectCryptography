package dssb.cryptography.encoder;

import java.util.Formatter;

public enum Hex implements Encoder {
    
    INSTANCE;
    
    public String encode(
            final byte[] hash) {
        Formatter formatter = new Formatter();
        int i = 0;
        for (byte b : hash) {
            formatter.format("%02x", b);
            if ((i % 4) == 3)
                formatter.format(" ");
            i++;
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
