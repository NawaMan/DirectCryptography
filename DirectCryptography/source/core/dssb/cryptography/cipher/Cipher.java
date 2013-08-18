package dssb.cryptography.cipher;

import dssb.cryptography.Cryptography;

/**
 * Cipher is a feature of a {@code Cryptography} that can provide {@code Encryptor} and {@code Decryptor}.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Cipher {
    
    /**
     * Returns the {@link Cryptography} used by this cipher.
     * 
     * @return the {@link Cryptography}.
     */
    public Cryptography getCryptography();
    
    /**
     * Returns an {@code Encryptor}.
     * 
     * @return an {@code Encryptor}.
     */
    public Encryptor getEncryptor();
    
    /**
     * Returns an {@code Decryptor}.
     * 
     * @return an {@code Decryptor}.
     */
    public Decryptor getDecryptor();
    
    // == Sub classes ==================================================================================================
    
    /** This class provide a simple implementation common to most {@code Cipher}. */
    public abstract static class Simple implements Cipher {
        
        /** This {@link Encryptor} implementation is used with {@code Cipher.Simple}. */
        public static class Encryptor extends dssb.cryptography.cipher.Encryptor.Simple {
            
            /**
             * Constructs an encryptor for the given cipher.
             * 
             * @param cipher
             *            the cipher.
             */
            public Encryptor(
                    final Cipher.Simple cipher) {
                super(cipher);
            }
            
            /** {@inheritDoc} **/
            @Override
            public byte[] encrypt(
                    final byte[] bytes)
                    throws EncyptionException {
                final Cipher.Simple cipher = ((Cipher.Simple) this.getCipher());
                final byte[] secret = cipher.encrypt(bytes);
                return secret;
            }
            
        }
        
        /** This {@link Decryptor} implementation is used with {@code Cipher.Simple}. */
        public static class Decryptor extends dssb.cryptography.cipher.Decryptor.Simple {
            
            /**
             * Constructs a decryptor for the given cipher.
             * 
             * @param cipher
             *            the cipher.
             */
            public Decryptor(
                    final Cipher.Simple cipher) {
                super(cipher);
            }
            
            /** {@inheritDoc} **/
            @Override
            public byte[] decrypt(
                    final byte[] bytes)
                    throws DecyptionException {
                final Cipher.Simple cipher = ((Cipher.Simple) this.getCipher());
                final byte[] secret = cipher.decrypt(bytes);
                return secret;
            }
            
        }
        
        /** The {@code Cryptography}. */
        private final Cryptography.WithCipher cryptography;
        
        /** The {@code Encryptor}. */
        private volatile Encryptor encryptor = null;
        
        /** The {@code Decryptor}. */
        private volatile Decryptor decryptor = null;
        
        /**
         * Constructor.
         * 
         * @param cryptography
         *            the {@code Cryptography} used by this {@code Cipher}.
         */
        public Simple(
                final Cryptography.WithCipher cryptography) {
            this.cryptography = cryptography;
        }
        
        /** {@inheritDoc} **/
        @Override
        public Cryptography.WithCipher getCryptography() {
            return this.cryptography;
        }
        
        /** {@inheritDoc} **/
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
        
        /** {@inheritDoc} **/
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
        
        /**
         * Create a new {@code Encryptor}.
         * 
         * @return a new {@code Encryptor}.
         */
        public abstract Encryptor newEncryptor();
        
        /**
         * Create a new {@code Decryptor}.
         * 
         * @return a new {@code Decryptor}.
         */
        public abstract Decryptor newDecryptor();
        
        /**
         * Encrypt the given bytes.
         * 
         * @param bytes
         *            the data bytes.
         * @return the encrypted bytes.
         * @throws EncyptionException
         *             problem during the encryption.
         */
        public abstract byte[] encrypt(
                final byte[] bytes)
                throws EncyptionException;
        
        /**
         * Decrypt the given bytes.
         * 
         * @param bytes
         *            the encrypted bytes.
         * @return the data bytes.
         * @throws DecyptionException
         *             problem during the encryption.
         */
        public abstract byte[] decrypt(
                final byte[] bytes)
                throws DecyptionException;
    }
    
}
