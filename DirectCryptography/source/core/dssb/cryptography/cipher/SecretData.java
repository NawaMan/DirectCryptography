package dssb.cryptography.cipher;

/**
 * This object encompass the encrypted data and the cipher used to encrypt it.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface SecretData {
    
    /**
     * Returns the {@code Cipher}.
     * 
     * @return the {@code Cipher}.
     */
    public Cipher getCipher();
    
    /**
     * Returns the encrypted data in bytes.
     * 
     * @return the encrypted data in bytes.
     */
    public byte[] getBytes();
    
}
