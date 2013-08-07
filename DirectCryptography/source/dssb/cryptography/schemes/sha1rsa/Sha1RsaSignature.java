package dssb.cryptography.schemes.sha1rsa;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;

import dssb.cryptography.Cryptography;
import dssb.cryptography.signature.SignException;
import dssb.cryptography.signature.Signature;
import dssb.cryptography.signature.VerifyException;

public class Sha1RsaSignature extends Signature.Simple {
    
    private PrivateKey privateKey = null;
    
    private PublicKey publicKey = null;
    
    public Sha1RsaSignature(
            final Cryptography.WithSignature cryptography,
            final PrivateKey privateKey,
            final PublicKey publicKey) {
        super(cryptography);
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
    
    @Override
    public Signer newSigner() {
        if (this.privateKey == null) {
            return null;
        } else {
            return new Signer(this);
        }
    }
    
    @Override
    public Verifier newVerifier() {
        if (this.publicKey == null) {
            return null;
        } else {
            return new Verifier(this);
        }
    }
    
    @Override
    public byte[] sign(
            final byte[] data)
            throws SignException {
        try {
            final java.security.Signature signature = java.security.Signature.getInstance("SHA1withRSA");
            final PrivateKey privateKey = Sha1RsaSignature.this.privateKey;
            signature.initSign(privateKey);
            signature.update(data);
            final byte[] fingerprint = signature.sign();
            return fingerprint;
        } catch (final NoSuchAlgorithmException problem) {
            throw new SignException(problem);
        } catch (final InvalidKeyException problem) {
            throw new SignException(problem);
        } catch (final SignatureException problem) {
            throw new SignException(problem);
        }
    }
    
    @Override
    public boolean verify(
            final byte[] data,
            final byte[] fingerprint)
            throws VerifyException {
        try {
            final java.security.Signature signature = java.security.Signature.getInstance("SHA1withRSA");
            signature.initVerify(this.publicKey);
            signature.update(data);
            
            final boolean isVerified = signature.verify(fingerprint);
            return isVerified;
        } catch (final NoSuchAlgorithmException problem) {
            throw new SignException(problem);
        } catch (final InvalidKeyException problem) {
            throw new SignException(problem);
        } catch (final SignatureException problem) {
            throw new SignException(problem);
        }
    }
    
}
