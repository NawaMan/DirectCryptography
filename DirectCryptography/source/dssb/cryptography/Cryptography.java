package dssb.cryptography;

import dssb.cryptography.cipher.Cipher;
import dssb.cryptography.signature.Signature;


public interface Cryptography {
    
    public Scheme getScheme();
    
    public WithCipher withCipher();
    
    public WithSignature withSigner();
    
    static public interface WithCipher extends Cryptography {
        
        public Cipher newCipher();
        
    }
    
    static public interface WithSignature extends Cryptography {
        
        public Signature newSignature();
        
    }
    
    // Save and Store
    // See http://snipplr.com/view/18368/
    // See http://stackoverflow.com/questions/5263156/rsa-keypair-generation-and-storing-to-keystore
    // See http://stackoverflow.com/questions/13894699/java-how-to-store-a-key-in-keystore
    
    
}
