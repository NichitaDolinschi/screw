package md.dolinschi.screw.reflection.exception;


public class NoArgsConstructorMissingException extends RuntimeException {
    public NoArgsConstructorMissingException() {
    }

    public NoArgsConstructorMissingException(String message) {
        super(message);
    }

    public NoArgsConstructorMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoArgsConstructorMissingException(Throwable cause) {
        super(cause);
    }

    public NoArgsConstructorMissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
