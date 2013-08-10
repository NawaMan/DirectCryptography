package dssb.cryptography.cipher;

import dssb.cryptography.Data;



public interface Encryptor {
    
    public Cipher getCipher();
    
    public byte[] encrypt(
            byte[] clearData) throws EncyptionException;
    
    public <_Type_> SecretData encrypt(
            Data<_Type_> clearData) throws EncyptionException;
    
    static abstract public class Simple implements Encryptor {
        
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
        public <_Type_> SecretData encrypt(
                final Data<_Type_> clearData) {
            final byte[] bytes = clearData.toBytes();
            final byte[] cipher = this.encrypt(bytes);
            final SecretData secret = this.newSecretData(cipher);
            return secret;
        }
        
        protected SecretData newSecretData(
                final byte[] cipher) {
            if (cipher == null) {
                throw new NullPointerException();
            }
            final SecretData secret = new SecretData() {
                
                @Override
                public Cipher getCipher() {
                    return Simple.this.getCipher();
                }
                
                @Override
                public byte[] getBytes() {
                    return cipher.clone();
                }
            };
            return secret;
        }
        
    }
    
}
