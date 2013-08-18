package dssb.cryptography.cipher;

import dssb.cryptography.Data;

/**
 * Classes implement this interface can encrypt data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Encryptor {
    
    /**
     * The cipher.
     * 
     * @return the cipher.
     */
    public Cipher getCipher();
    
    /**
     * Encrypt the given clear data and return bytes.
     * 
     * @param clearData
     *            the clear data.
     * @return the encrypted bytes.
     * @throws EncyptionException
     *             when there is a problem with encrypting the clear data.
     */
    public byte[] encrypt(
            byte[] clearData)
            throws EncyptionException;
    
    /**
     * Encrypt the data and return a {@code SecretData} object.
     * 
     * @param clearData
     *            the clear data.
     * @param <_Type_>
     *            the type of the clear data.
     * @return the secret data.
     * @throws EncyptionException
     *             when there is a problem with encrypting the clear data.
     */
    public <_Type_> SecretData encrypt(
            Data<_Type_> clearData)
            throws EncyptionException;
    
    // == Sub classes ==================================================================================================
    
    /**
     * Simple implementation for {@code Encryptor}.
     */
    public abstract static class Simple implements Encryptor {
        
        /** The {@code Cipher}. */
        private final Cipher cipher;
        
        /**
         * Constructors.
         * 
         * @param cipher
         *            the {@code Cipher}
         */
        public Simple(
                final Cipher cipher) {
            if (cipher == null) {
                throw new NullPointerException();
            }
            this.cipher = cipher;
        }
        
        /** {@inheritDoc} */
        @Override
        public Cipher getCipher() {
            return this.cipher;
        }
        
        @Override
        public <_Type_> SecretData encrypt(
                final Data<_Type_> clearData) {
            final byte[] bytes = clearData.toBytes();
            final byte[] secretBytes = this.encrypt(bytes);
            final SecretData secret = this.newSecretData(secretBytes);
            return secret;
        }
        
        /**
         * Create a new secret data from the secret bytes.
         * @param secretBytes the secret data in bytes.
         * @return the {@code SecretData}.
         **/
        protected SecretData newSecretData(
                final byte[] secretBytes) {
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
                    return secretBytes.clone();
                }
            };
            return secret;
        }
        
    }
    
}
