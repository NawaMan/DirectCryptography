package dssb.cryptography.encoder;

public interface Encoder {
    
    public String encode(byte[] data);
    
    public byte[] decode(String base64);
    
}
