package by.iba.markovsky.festivalorganisation.infrastructure.exception;

public class CustomQueueOverflowException extends Exception {

    public CustomQueueOverflowException() {
        super();
    }
    public CustomQueueOverflowException(String message) {
        super(message);
    }

}
