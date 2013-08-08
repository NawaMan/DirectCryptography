package dssb.cryptography;

import org.junit.Test;

import dssb.cryptography.datatypes.SerializeType;
import dssb.cryptography.datatypes.Text;
import dssb.cryptography.schemes.sha1rsa.Sha1RsaCryptographyBuilder;
import dssb.cryptography.schemes.sha1rsa.Sha1RsaScheme;
import dssb.cryptography.signature.Fingerprint;
import dssb.cryptography.signature.Signature;
import dssb.cryptography.signature.Signer;
import dssb.cryptography.signature.Verifier;

public class TestSha1Rsa {
    
    @Test
    public void test() throws Exception {
        final Sha1RsaCryptographyBuilder cryptBuilder = Sha1RsaScheme.INSTANCE.createCryptographyBuilder();
        cryptBuilder.useNewKeyPair();
        
        final Signature signature = cryptBuilder.newCryptography().withSigner().newSignature();
        final Signer signer = signature.getSigner();
        final Verifier verifier = signature.getVerifier();
        
        final SerializeType<Integer> INT = new SerializeType<Integer>(Integer.class);
        
        final Text data1 = new Text("Some text here.");
        final Fingerprint fingerprint1 = signer.sign(data1);
        final Data<Integer> data2 = new Data.Simple<Integer>(INT, 5);
        final Fingerprint fingerprint2 = signer.sign(data2);
        System.out.println(verifier.verify(data1, fingerprint1));
        System.out.println(verifier.verify(data2, fingerprint2));
    }
    
}
