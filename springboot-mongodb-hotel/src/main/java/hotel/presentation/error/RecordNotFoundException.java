package hotel.presentation.error;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String exception) {
        super(exception);
    }

}
