package md.dolinschi.screw.reflection.exception;

public class CollectionNotCompatibleType extends RuntimeException {
    public CollectionNotCompatibleType() {
    }

    public CollectionNotCompatibleType(String message) {
        super(message);
    }

    public CollectionNotCompatibleType(String message, Throwable cause) {
        super(message, cause);
    }

    public CollectionNotCompatibleType(Throwable cause) {
        super(cause);
    }

    public CollectionNotCompatibleType(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
