package dssb.cryptography.signature;

public interface Fingerprint {
    
    public Signature getSignature();
    
    public byte[] getBytes();
    
    static public class Simple implements Fingerprint {
        
        private final byte[] bytes;
        
        private final Signature signature;
        
        public Simple(
                final Signature signature,
                final byte[] bytes) {
            if (signature == null) {
                throw new NullPointerException();
            }
            this.signature = signature;
            this.bytes = bytes.clone();
        }
        
        @Override
        final public Signature getSignature() {
            // TODO Auto-generated method stub
            return this.signature;
        }
        
        @Override
        public byte[] getBytes() {
            return this.bytes.clone();
        }
    }
}
