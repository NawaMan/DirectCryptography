package dssb.cryptography.signature;

public class VerifyException extends RuntimeException {
    
    public VerifyException(
            String message,
            Throwable cause) {
        super(message, cause);
    }
    
    public VerifyException(
            String message) {
        super(message, null);
    }
    
    public VerifyException(
            Throwable cause) {
        super(null, cause);
    }
    
    public VerifyException() {
        super(null, null);
    }
    
}
