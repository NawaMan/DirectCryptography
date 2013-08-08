package dssb.cryptography;


public interface Type<_Type_> {
    
    public Class<_Type_> getDataClass();
    
    public byte[] toBytes(_Type_ data);
    
    public byte[] toBytes(Data<_Type_> data);
    
    public _Type_ fromBytes(byte[] bytes);
    
    public Data<_Type_> toClearData(_Type_ data);
    
    public Data<_Type_> toClearData(byte[] bytes);
    
    static abstract public class Simple<_Type_> implements Type<_Type_> {
        
        public byte[] toBytes(Data<_Type_> data) {
            final _Type_ raw = data.getData();
            final byte[] bytes = this.toBytes(raw);
            return bytes;
        }
        
        public Data<_Type_> toClearData(byte[] bytes) {
            final _Type_ data = this.fromBytes(bytes);
            final Data<_Type_> clearData = this.toClearData(data);
            return clearData;
        }
        
    }
    
    
}
