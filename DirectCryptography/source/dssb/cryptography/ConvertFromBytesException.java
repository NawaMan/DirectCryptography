package dssb.cryptography;

public class ConvertFromBytesException extends DataConversionException {
    
    public ConvertFromBytesException(
            String message,
            Throwable cause) {
        super(message, cause);
    }
    
    public ConvertFromBytesException(
            String message) {
        super(message, null);
    }
    
    public ConvertFromBytesException(
            Throwable cause) {
        super(null, cause);
    }
    
    public ConvertFromBytesException() {
        super(null, null);
    }
    
}
