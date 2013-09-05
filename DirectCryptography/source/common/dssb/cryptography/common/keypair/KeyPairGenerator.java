package dssb.cryptography.common.keypair;

import java.security.KeyPair;

/**
 * Classes implements this interface can create a key pair.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 **/
public interface KeyPairGenerator {
    
    /**
     * Generate a key pair.
     * 
     * @return a newly created key pair.
     **/
    public KeyPair generate();
    
}
