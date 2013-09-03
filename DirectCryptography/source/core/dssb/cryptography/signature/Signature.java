package dssb.cryptography.signature;

import dssb.cryptography.Cryptography;
import dssb.cryptography.Cryptography.Feature;
import dssb.cryptography.signature.Signature.Simple.Signer;

/**
 * Classes implementing this interface can create a signer and associated verifier.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Signature extends Feature<Signature> {
    
    /**
     * Returns the {@link Cryptography} used by this signature.
     * 
     * @return the {@link Cryptography}.
     */
    public Cryptography getCryptography();
    
    /**
     * Returns the {@link Signer}.
     * 
     * @return the {@link Signer}.
     */
    public Signer getSigner();
    
    /**
     * Returns the {@link Verifier}.
     * 
     * @return the {@link Verifier}.
     */
    public Verifier getVerifier();
    
    // == Sub classes ==================================================================================================
    
    /** This class provide a simple implementation common to most {@code Signature}. */
    public abstract static class Simple implements Signature {
        
        /** This {@link Signer} implementation is used with {@code Signature.Simple}. */
        public static class Signer extends dssb.cryptography.signature.Signer.Simple {
            
            /**
             * Constructs.
             * 
             * @param signature
             *            the signature.
             */
            public Signer(
                    final Signature.Simple signature) {
                super(signature);
            }
            
            /** {@inheritDoc} */
            @Override
            public byte[] signToBytes(
                    final byte[] data)
                    throws SignException {
                final Signature.Simple signature = ((Signature.Simple) this.getSignature());
                final byte[] fingerprint = signature.sign(data);
                return fingerprint;
            }
            
        }
        
        /** This {@link Verifier} implementation is used with {@code Signature.Simple}. */
        public static class Verifier extends dssb.cryptography.signature.Verifier.Simple {
            
            /**
             * Constructs.
             * 
             * @param signature
             *            the signature.
             */
            public Verifier(
                    final Signature.Simple signature) {
                super(signature);
            }
            
            /** {@inheritDoc} */
            @Override
            public boolean verify(
                    final byte[] dataBytes,
                    final byte[] fingerprintBytes)
                    throws VerifyException {
                final Signature.Simple signature = ((Signature.Simple) this.getSignature());
                final boolean isVerified = signature.verify(dataBytes, fingerprintBytes);
                return isVerified;
            }
            
        }
        
        /** The {@link Cryptography} used. */
        private final Cryptography cryptography;
        
        /** Cache of the {@link Signer}. */
        private volatile Signer signer = null;
        
        /** Cache of the {@link Verifier}. */
        private volatile Verifier verifier = null;
        
        /**
         * Constructor.
         * 
         * @param cryptography
         *            the {@link Cryptography} used.
         */
        public Simple(
                final Cryptography cryptography) {
            if (cryptography == null) {
                throw new NullPointerException();
            }
            this.cryptography = cryptography;
        }
        
        /** {@inheritDoc} */
        @Override
        public Cryptography getCryptography() {
            return this.cryptography;
        }
        
        /** {@inheritDoc} */
        @Override
        public Signer getSigner() {
            if (this.signer != null) {
                return this.signer;
            }
            
            synchronized (this) {
                if (this.signer != null) {
                    return this.signer;
                }
                
                this.signer = this.newSigner();
                return this.signer;
            }
        }
        
        /** {@inheritDoc} */
        @Override
        public Verifier getVerifier() {
            if (this.verifier != null) {
                return this.verifier;
            }
            
            synchronized (this) {
                if (this.verifier != null) {
                    return this.verifier;
                }
                
                this.verifier = this.newVerifier();
                return this.verifier;
            }
        }
        
        /**
         * Create a new {@link Signer}.
         * 
         * @return a new {@link Signer}.
         */
        public Signer newSigner() {
            return new Signer(this);
        }
        
        /**
         * Create a new {@link Verifier}.
         * 
         * @return a new {@link Verifier}.
         */
        public Verifier newVerifier() {
            return new Verifier(this);
        }
        
        /**
         * Sign the given data.
         * 
         * @param data
         *            the input data.
         * @return the fingerprint in bytes.
         * @throws SignException
         *             any problem found while signing.
         */
        public abstract byte[] sign(
                final byte[] data)
                throws SignException;
        
        /**
         * Verify the given data and finger print.
         * 
         * @param data
         *            the input data.
         * @param fingerprint
         *            the fingerprint.
         * @return {@code true} if the fingerprint is confirm the integrity of the input data.
         * @throws VerifyException
         *             any problem found while verifying.
         */
        public abstract boolean verify(
                final byte[] data,
                final byte[] fingerprint)
                throws VerifyException;
    }
}
