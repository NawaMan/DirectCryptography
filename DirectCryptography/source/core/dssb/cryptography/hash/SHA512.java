package dssb.cryptography.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dssb.cryptography.Data;
import dssb.cryptography.encoder.Hex;

public enum SHA512 implements Hasher {
    
    _,
    SHA512,
    INSTANCE;

    @Override
    public <_Type_> byte[] hash(
            Data<_Type_> data) {
        final byte[] bytes = data.toBytes();
        final byte[] hash = this.hash(bytes);
        return hash;
    }

    @Override
    public <_Type_> byte[] hash(
            byte[] data) {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-512");
            final byte[] hash = md.digest(data);
            return hash;
        } catch (final NoSuchAlgorithmException problem) {
            // TODO - Assign problem exception.
            throw new RuntimeException(problem);
        }
    }
    
    static public void main(final String ... args) {
        System.out.println(Hex.INSTANCE.encode(SHA512.INSTANCE.hash("Hello world!!!".getBytes())));
    }
    
    
}
