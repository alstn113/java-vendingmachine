package vendingmachine.exception;

public class BaseException extends IllegalArgumentException {
    private static final String PREFIX = "[ERROR]";

    public BaseException(String message) {

        super(String.format("%s %s", PREFIX, message));
    }
}
