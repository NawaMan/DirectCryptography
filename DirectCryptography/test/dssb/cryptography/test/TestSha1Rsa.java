package dssb.cryptography.test;

import junit.framework.Assert;

import org.junit.Test;

import dssb.cryptography.Data;
import dssb.cryptography.datatypes.SerializableType;
import dssb.cryptography.datatypes.TextType;
import dssb.cryptography.schemes.sha1rsa.Sha1RsaCryptographyBuilder;
import dssb.cryptography.schemes.sha1rsa.Sha1RsaScheme;
import dssb.cryptography.signature.Fingerprint;
import dssb.cryptography.signature.Signature;
import dssb.cryptography.signature.Signer;
import dssb.cryptography.signature.Verifier;

/**
 * Test for SHA1-RSA.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class TestSha1Rsa {
    
    /** Test. */
    @Test
    public void test() {
        final Sha1RsaCryptographyBuilder cryptBuilder = Sha1RsaScheme.INSTANCE.createCryptographyBuilder();
        cryptBuilder.useNewKeyPair();
        
        final Signature signature = cryptBuilder.newCryptography().getFeature(Signature.class);
        final Signer signer = signature.getSigner();
        final Verifier verifier = signature.getVerifier();
        
        final SerializableType<Integer> intType = new SerializableType<Integer>(Integer.class);
        
        final TextType.Data data1 = new TextType.Data("Some text here.");
        final Fingerprint fingerprint1 = signer.sign(data1);
        final Data<Integer> data2 = new Data.Simple<Integer>(intType, 5);
        final Fingerprint fingerprint2 = signer.sign(data2);
        Assert.assertTrue(verifier.verify(data1, fingerprint1));
        Assert.assertTrue(verifier.verify(data2, fingerprint2));
    }
    
}
