package dssb.cryptography.cipher;

import dssb.cryptography.Data;
import dssb.cryptography.Type;



public interface Decryptor {
    
    public Cipher getCipher();
    
    public byte[] decrypt(SecretData secret);
    
    public <_Type_> Data<_Type_> decrypt(SecretData content, Type<_Type_> dataType);
    
    public byte[] decrypt(byte[] secret);
    
    static abstract public class Simple implements Decryptor {
        
        private Cipher cipher;
        
        public Simple(
                final Cipher cipher) {
            if (cipher == null) {
                throw new NullPointerException();
            }
            this.cipher = cipher;
        }
        
        @Override
        public Cipher getCipher() {
            return this.cipher;
        }
        
        @Override
        public byte[] decrypt(
                final SecretData secret) {
            final byte[] bytes = secret.getBytes();
            final byte[] data = this.decrypt(bytes);
            return data;
        }
        
        @Override
        public <_Type_> Data<_Type_> decrypt(SecretData secret, Type<_Type_> dataType) {
            final byte[] bytes = this.decrypt(secret);
            final Data<_Type_> clearData = dataType.toClearData(bytes);
            return clearData;
        }
        
    }
}
