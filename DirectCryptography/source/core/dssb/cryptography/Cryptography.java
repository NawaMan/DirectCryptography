package dssb.cryptography;

import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.signature.Signature;

/**
 * Classes implement this class represent cryptography information needed to do cryptography operations.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Cryptography {
    
    /**
     * Returns the cryptography scheme for this chryptography.
     * 
     * @return the scheme.
     */
    public Scheme getScheme();
    
    /**
     * Returns {@link WithCipher} object if this {@code Cryptography} can be used to create a {@code Cipher}.
     * 
     * @return the {@link WithCipher} object or {@code null}.
     */
    public WithCipher withCipher();
    
    /**
     * Checks if this {@link Cryptography} has a {@link Cipher}.
     * 
     * @return {@code true} if it does.
     **/
    public boolean hasCipher();
    
    /**
     * Returns {@link WithSignature} object if this {@code Cryptography} can be used to create a {@code Signature}.
     * 
     * @return the {@link WithSignature} object or {@code null}.
     */
    public WithSignature withSignature();
    
    /**
     * Checks if this {@link Cryptography} has a {@link Signature}.
     * 
     * @return {@code true} if it does.
     **/
    public boolean hasSignature();
    
    //== Sub classes ===================================================================================================
    
    /**
     * {@link Cryptography} that implements this interface can create a {@link Cipher}.
     */
    public static interface WithCipher extends Cryptography {
        
        /**
         * Returns a newly created {@link Cipher} that uses the information from this {@link Cryptography}.
         * 
         * @return a newly created {@link Cipher}.
         */
        public Cipher newCipher();
        
    }
    
    /**
     * {@link Cryptography} that implements this interface can create a {@link Signature}.
     */
    public static interface WithSignature extends Cryptography {

        /**
         * Returns a newly created {@link Signature} that uses the information from this {@link Cryptography}.
         * 
         * @return a newly created {@link Signature}.
         */
        public Signature newSignature();
        
    }
    
    // TODO
    // Save and Store
    // See http://snipplr.com/view/18368/
    // See http://stackoverflow.com/questions/5263156/rsa-keypair-generation-and-storing-to-keystore
    // See http://stackoverflow.com/questions/13894699/java-how-to-store-a-key-in-keystore
    
}
