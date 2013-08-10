package dssb.cryptography.hash;

import dssb.cryptography.Data;

public interface Hasher {
    
    public <_Type_> byte[] hash(Data<_Type_> data);
    
    public <_Type_> byte[] hash(byte[] data);
    
}
