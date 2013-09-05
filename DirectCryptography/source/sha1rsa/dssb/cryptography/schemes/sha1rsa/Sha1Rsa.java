package dssb.cryptography.schemes.sha1rsa;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;

import dssb.cryptography.cipher.DecyptionException;
import dssb.cryptography.cipher.EncyptionException;
import dssb.cryptography.javascrypto.JavaCryptoKeyPairScheme;
import dssb.cryptography.schemes.rsa.Rsa;
import dssb.cryptography.schemes.rsa.AbstractRsaCryptographyBuilder;
import dssb.cryptography.signature.SignException;
import dssb.cryptography.signature.Signature;
import dssb.cryptography.signature.VerifyException;

/**
 * RSA cryptography scheme.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class Sha1Rsa extends JavaCryptoKeyPairScheme.Simple<Sha1Rsa> {
    
    /** The SHA1+RSA signature algorithm name. */
    private static final String SHA1RSA_ALGORITHM_NAME = "SHA1withRSA";
    
    /** Static import instance. */
    public static final Sha1Rsa Sha1Rsa = new Sha1Rsa();
    
    /** Shorthand instance. */
    public static final Sha1Rsa _ = Sha1Rsa;
    
    /** Semantic instance. */
    public static final Sha1Rsa Scheme = Sha1Rsa;
    
    /** Conventional instance. */
    public static final Sha1Rsa INSTANCE = Sha1Rsa;
    
    /**
     * The builder for RSA cryptography.
     */
    public static class CryptographyBuilder extends AbstractRsaCryptographyBuilder<Sha1Rsa> {
        /** Constructor. */
        CryptographyBuilder() {
            super(Sha1Rsa);
        }
        
        /** {@inheritDoc} */
        @Override
        public Sha1Rsa.Cryptography newCryptography() {
            return new Sha1Rsa.Cryptography(this);
        }
    }
    
    /** Sha1Rsa cryptography. */
    public static class Cryptography extends JavaCryptoKeyPairScheme.Cryptography<Sha1Rsa> {
        
        /**
         * Constructor.
         * 
         * @param builder
         *            the cryptography builder.
         */
        public Cryptography(
                final Builder<Sha1Rsa> builder) {
            super(builder);
        }
        
        /** {@inheritDoc} */
        @Override
        protected Signature newSignature() {
            final PrivateKey privateKey = this.getPrivateKey();
            final PublicKey publicKey = this.getPublicKey();
            final Signature signature = new Signature.Simple(this) {
                
                /** {@inheritDoc} */
                @Override
                public byte[] sign(
                        final byte[] data)
                        throws SignException {
                    final byte[] fignerprint = ((Sha1Rsa) this.getCryptography().getScheme()).sign(privateKey, data);
                    return fignerprint;
                }
                
                /** {@inheritDoc} */
                @Override
                public boolean verify(
                        final byte[] data,
                        final byte[] fingerprint)
                        throws VerifyException {
                    final boolean isVerified = ((Sha1Rsa) this.getCryptography().getScheme()).verify(publicKey, data,
                            fingerprint);
                    return isVerified;
                }
            };
            return signature;
        }
    }
    
    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public CryptographyBuilder createCryptographyBuilder() {
        final CryptographyBuilder builder = new CryptographyBuilder();
        return builder;
    }
    
    /** {@inheritDoc} */
    @Override
    public byte[] encrypt(
            final PublicKey publicKey,
            final byte[] clearData)
            throws EncyptionException {
        final byte[] bytes = Rsa._.encrypt(publicKey, clearData);
        return bytes;
    }
    
    /** {@inheritDoc} */
    @Override
    public byte[] decrypt(
            final PrivateKey privateKey,
            final byte[] encryptedData)
            throws DecyptionException {
        final byte[] bytes = Rsa._.decrypt(privateKey, encryptedData);
        return bytes;
    }
    
    /**
     * Sign the given data.
     * 
     * @param privateKey
     *            the private key.
     * @param data
     *            the input data.
     * @return the fingerprint bytes.
     * @throws SignException
     *             when there is a problem signing the data.
     */
    public byte[] sign(
            final PrivateKey privateKey,
            final byte[] data)
            throws SignException {
        try {
            final java.security.Signature signature = java.security.Signature.getInstance(SHA1RSA_ALGORITHM_NAME);
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
    
    /**
     * Verify if the signed data.
     * 
     * @param publicKey
     *            the public key.
     * @param data
     *            the data in bytes.
     * @param fingerprint
     *            the fingerprint in bytes.
     * @return the verification result.
     * @throws VerifyException
     *             when there is a problem verifying.
     */
    public boolean verify(
            final PublicKey publicKey,
            final byte[] data,
            final byte[] fingerprint)
            throws VerifyException {
        try {
            final java.security.Signature signature = java.security.Signature.getInstance(SHA1RSA_ALGORITHM_NAME);
            signature.initVerify(publicKey);
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
