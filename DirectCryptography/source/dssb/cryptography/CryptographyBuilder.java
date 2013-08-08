package dssb.cryptography;

/**
 * Classes implement this interface can create cryptography.
 * 
 * Any implementation is expected provide ways to customize the cryptography to be created.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface CryptographyBuilder {
    
    /**
     * Returns a newly created {@link Cryptography}.
     * 
     * @return a newly created {@link Cryptography}.
     */
    public Cryptography newCryptography();
    
}
