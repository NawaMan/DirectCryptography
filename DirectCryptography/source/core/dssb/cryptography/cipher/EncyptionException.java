package dssb.cryptography.cipher;

public class EncyptionException extends RuntimeException {
    
    public EncyptionException(
            String message,
            Throwable cause) {
        super(message, cause);
    }
    
    public EncyptionException(
            String message) {
        super(message, null);
    }
    
    public EncyptionException(
            Throwable cause) {
        super(null, cause);
    }
    
    public EncyptionException() {
        super(null, null);
    }
    
}
