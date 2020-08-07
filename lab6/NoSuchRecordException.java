import java.lang.Exception;

class NoSuchRecordException extends Exception {
    public NoSuchRecordException(String message) {
        super(message); 
    }
}
