package dssb.cryptography.signature;

public class SignException extends RuntimeException {
    
    public SignException(
            String message,
            Throwable cause) {
        super(message, cause);
    }
    
    public SignException(
            String message) {
        super(message, null);
    }
    
    public SignException(
            Throwable cause) {
        super(null, cause);
    }
    
    public SignException() {
        super(null, null);
    }
    
}
