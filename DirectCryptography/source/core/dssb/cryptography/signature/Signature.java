package dssb.cryptography.signature;

import dssb.cryptography.Cryptography;

public interface Signature {
    
    public Cryptography.WithSignature getCryptography();
    
    public Signer getSigner();
    
    public Verifier getVerifier();
    
    static abstract public class Simple implements Signature {
        
        static public class Signer extends dssb.cryptography.signature.Signer.Simple {
            
            public Signer(
                    final Signature.Simple signature) {
                super(signature);
            }
            
            @Override
            public byte[] signToBytes(
                    final byte[] data)
                    throws SignException {
                final Signature.Simple signature = ((Signature.Simple)this.getSignature());
                final byte[] fingerprint = signature.sign(data);
                return fingerprint;
            }
            
        }
        
        static public class Verifier extends dssb.cryptography.signature.Verifier.Simple {
            
            public Verifier(
                    final Signature.Simple signature) {
                super(signature);
            }
            
            @Override
            public boolean verify(
                    byte[] dataBytes,
                    byte[] fingerprintBytes)
                    throws VerifyException {
                final Signature.Simple signature = ((Signature.Simple)this.getSignature());
                final boolean isVerified = signature.verify(dataBytes, fingerprintBytes);
                return isVerified;
            }
            
        }
        
        private final Cryptography.WithSignature cryptography;
        
        volatile private Signer signer = null;
        
        volatile private Verifier verifier = null;
        
        public Simple(
                final Cryptography.WithSignature cryptography) {
            if (cryptography == null) {
                throw new NullPointerException();
            }
            this.cryptography = cryptography;
        }
        
        @Override
        public Cryptography.WithSignature getCryptography() {
            return this.cryptography;
        }
        
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
        
        abstract public Signer newSigner();
        
        abstract public Verifier newVerifier();
        
        abstract public byte[] sign(
                final byte[] data)
                throws SignException;
        
        abstract public boolean verify(
                final byte[] data,
                final byte[] fingerprint)
                throws VerifyException;
    }
}
