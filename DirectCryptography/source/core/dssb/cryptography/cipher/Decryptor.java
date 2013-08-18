package dssb.cryptography.cipher;

import dssb.cryptography.Data;
import dssb.cryptography.Type;

/**
 * Classes implement this interface can decrypt data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Decryptor {
    
    /**
     * The cipher.
     * 
     * @return the cipher.
     */
    public Cipher getCipher();
    
    /**
     * Decrypt the given secret data to a clear data in bytes.
     * 
     * @param secret
     *            the secret.
     * @return the encrypted bytes.
     * @throws DecyptionException
     *             when there is a problem with encrypting the clear data.
     */
    public byte[] decrypt(
            SecretData secret)
            throws DecyptionException;
    
    /**
     * Decrypt the given secret data to a clear data in {@code Data}.
     * 
     * @param secret
     *            the secret.
     * @param dataType
     *            the expected data type.
     * @param <_Type_>
     *            the type of the data.
     * @return the encrypted bytes.
     * @throws DecyptionException
     *             when there is a problem with encrypting the clear data.
     */
    public <_Type_> Data<_Type_> decrypt(
            SecretData secret,
            Type<_Type_> dataType)
            throws DecyptionException;
    
    /**
     * Decrypt the given secret data in bytes to a clear data in bytes.
     * 
     * @param secret
     *            the secret in bytes.
     * @return the encrypted bytes.
     * @throws DecyptionException
     *             when there is a problem with encrypting the clear data.
     */
    public byte[] decrypt(
            byte[] secret)
            throws DecyptionException;
    
    // == Sub classes ==================================================================================================
    
    /**
     * Simple implementation for {@code Decryptor}.
     */
    public abstract static class Simple implements Decryptor {
        
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
        
        /** {@inheritDoc} */
        @Override
        public byte[] decrypt(
                final SecretData secret) {
            final byte[] bytes = secret.getBytes();
            final byte[] data = this.decrypt(bytes);
            return data;
        }
        
        /** {@inheritDoc} */
        @Override
        public <_Type_> Data<_Type_> decrypt(
                final SecretData secret,
                final Type<_Type_> dataType) {
            final byte[] bytes = this.decrypt(secret);
            final Data<_Type_> clearData = dataType.toDataFromByte(bytes);
            return clearData;
        }
        
    }
}
