package dssb.cryptography.common.secretkey;

import javax.crypto.SecretKey;

/**
 * Classes implements this interface can create a secret key.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 **/
public interface SecretKeyGenerator {
    
    /**
     * Generate a secret key.
     * 
     * @return a newly created secret key.
     **/
    public SecretKey generate();
    
}
