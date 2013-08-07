package dssb.cryptography;

public class ConvertToBytesException extends DataConversionException {
    
    public ConvertToBytesException(
            String message,
            Throwable cause) {
        super(message, cause);
    }
    
    public ConvertToBytesException(
            String message) {
        super(message, null);
    }
    
    public ConvertToBytesException(
            Throwable cause) {
        super(null, cause);
    }
    
    public ConvertToBytesException() {
        super(null, null);
    }
    
}
