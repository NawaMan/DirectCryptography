package dssb.cryptography.cipher;

public class DecyptionException extends RuntimeException {
    
    public DecyptionException(
            String message,
            Throwable cause) {
        super(message, cause);
    }
    
    public DecyptionException(
            String message) {
        super(message, null);
    }
    
    public DecyptionException(
            Throwable cause) {
        super(null, cause);
    }
    
    public DecyptionException() {
        super(null, null);
    }
    
}
