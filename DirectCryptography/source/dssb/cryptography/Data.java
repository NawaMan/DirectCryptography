package dssb.cryptography;

public interface Data<_Type_> {
    
    public DataType<_Type_> getDataType();
    
    public _Type_ getData();
    
    public byte[] toBytes();
    
    static public class Simple<_Type_> implements Data<_Type_> {
        
        private DataType<_Type_> dataType;
        
        private _Type_ data;
        
        public Simple(
                final DataType<_Type_> dataType,
                final _Type_ data) {
            this.dataType = dataType;
            this.data = data;
        }
        
        public DataType<_Type_> getDataType() {
            return this.dataType;
        }
        
        public _Type_ getData() {
            return this.data;
        }
        
        public byte[] toBytes() {
            return this.getDataType().toBytes(this);
        }
        
    }
    
}
