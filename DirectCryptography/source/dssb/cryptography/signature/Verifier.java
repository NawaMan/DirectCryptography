package dssb.cryptography.signature;

import dssb.cryptography.Data;

public interface Verifier {
    
    public Signature getSignature();
    
    public boolean verify(
            final byte[] dataBytes,
            final byte[] fingerprintBytes)
            throws VerifyException;
    
    public boolean verify(
            final byte[] dataBytes,
            final Fingerprint fingerprint)
            throws VerifyException;
    
    public <_Type_> boolean verify(
            final Data<_Type_> data,
            final byte[] fingerprintBytes)
            throws VerifyException;
    
    public <_Type_> boolean verify(
            final Data<_Type_> data,
            final Fingerprint fingerprint)
            throws VerifyException;
    
    static public abstract class Simple implements Verifier {
        
        private final Signature signature;
        
        public Simple(
                final Signature signature) {
            this.signature = signature;
        }
        
        final public Signature getSignature() {
            return this.signature;
        }
        
        @Override
        public boolean verify(
                final byte[] dataBytes,
                final Fingerprint fingerprint)
                throws VerifyException {
            final byte[] fingerprintBytes = fingerprint.getBytes();
            final boolean isVerified = this.verify(dataBytes, fingerprintBytes);
            return isVerified;
        }
        
        @Override
        public <_Type_> boolean verify(
                final Data<_Type_> data,
                final byte[] fingerprintBytes)
                throws VerifyException {
            final byte[] bytes = data.toBytes();
            final boolean isVerified = this.verify(bytes, fingerprintBytes);
            return isVerified;
        }
        
        @Override
        public <_Type_> boolean verify(
                final Data<_Type_> data,
                final Fingerprint fingerprint)
                throws VerifyException {
            final byte[] dataBytes = data.toBytes();
            final byte[] fingerprintBytes = fingerprint.getBytes();
            final boolean isVerified = this.verify(dataBytes, fingerprintBytes);
            return isVerified;
        }
        
    }
    
}
