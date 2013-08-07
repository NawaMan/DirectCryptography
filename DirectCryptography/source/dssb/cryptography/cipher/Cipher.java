package dssb.cryptography.cipher;

import dssb.cryptography.Cryptography;

public interface Cipher {
    
    public Cryptography getCryptography();
    
    public Encryptor getEncryptor();
    
    public Decryptor getDecryptor();
    
    static abstract public class Simple implements Cipher {
        
        static public class Encryptor extends dssb.cryptography.cipher.Encryptor.Simple {
            
            public Encryptor(
                    final Cipher.Simple cipher) {
                super(cipher);
            }
            
            @Override
            public byte[] encrypt(
                    final byte[] bytes)
                    throws EncyptionException {
                final Cipher.Simple cipher = ((Cipher.Simple)this.getCipher());
                final byte[] secret = cipher.encrypt(bytes);
                return secret;
            }
            
        }
        
        static public class Decryptor extends dssb.cryptography.cipher.Decryptor.Simple {
            
            public Decryptor(
                    final Cipher.Simple cipher) {
                super(cipher);
            }
            
            @Override
            public byte[] decrypt(
                    final byte[] bytes)
                    throws DecyptionException {
                final Cipher.Simple cipher = ((Cipher.Simple)this.getCipher());
                final byte[] secret = cipher.decrypt(bytes);
                return secret;
            }
            
        }
        
        private final Cryptography.WithCipher cryptography;
        
        volatile private Encryptor encryptor = null;
        
        volatile private Decryptor decryptor = null;
        
        public Simple(
                final Cryptography.WithCipher cryptography) {
            this.cryptography = cryptography;
        }
        
        @Override
        public Cryptography.WithCipher getCryptography() {
            return this.cryptography;
        }
        
        @Override
        public Encryptor getEncryptor() {
            if (this.encryptor != null) {
                return this.encryptor;
            }
            
            synchronized (this) {
                if (this.encryptor != null) {
                    return this.encryptor;
                }
                
                this.encryptor = this.newEncryptor();
                return this.encryptor;
            }
        }
        
        @Override
        public Decryptor getDecryptor() {
            if (this.decryptor != null) {
                return this.decryptor;
            }
            
            synchronized (this) {
                if (this.decryptor != null) {
                    return this.decryptor;
                }
                
                this.decryptor = this.newDecryptor();
                return this.decryptor;
            }
        }
        
        abstract public Encryptor newEncryptor();
        
        abstract public Decryptor newDecryptor();
        
        abstract public byte[] encrypt(
                final byte[] bytes)
                throws EncyptionException;
        
        abstract public byte[] decrypt(
                final byte[] bytes)
                throws EncyptionException;
    }
    
}
