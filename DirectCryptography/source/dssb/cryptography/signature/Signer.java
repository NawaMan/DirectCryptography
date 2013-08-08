package dssb.cryptography.signature;

import dssb.cryptography.Data;
import dssb.cryptography.Type;

public interface Signer {
    
    public Signature getSignature();
    
    public byte[] signToBytes(
            byte[] data)
            throws SignException;
    
    public <_Type_> byte[] signToBytes(
            final Data<_Type_> data)
            throws SignException;
    
    public Fingerprint sign(
            byte[] data)
            throws SignException;
    
    public <_Type_> Fingerprint sign(
            final Data<_Type_> data)
            throws SignException;
    
    static abstract public class Simple implements Signer {
        
        private final Signature signature;
        
        public Simple(
                final Signature signature) {
            this.signature = signature;
        }
        
        final public Signature getSignature() {
            return this.signature;
        }
        
        @Override
        public <_Type_> byte[] signToBytes(
                Data<_Type_> data)
                throws SignException {
            final Type<_Type_> type = data.getType();
            final byte[] bytes = type.toBytes(data);
            final byte[] fingerprint = this.signToBytes(bytes);
            return fingerprint;
        }
        
        public Fingerprint sign(
                byte[] data)
                throws SignException {
            final byte[] fingerprintBytes = this.signToBytes(data);
            final Fingerprint fingerprint = new Fingerprint.Simple(this.signature, fingerprintBytes);
            return fingerprint;
        }
        
        public <_Type_> Fingerprint sign(
                final Data<_Type_> data)
                throws SignException {
            final byte[] dataBytes = data.toBytes();
            final Fingerprint fingerprint = this.sign(dataBytes);
            return fingerprint;
        }
    }
    
}
