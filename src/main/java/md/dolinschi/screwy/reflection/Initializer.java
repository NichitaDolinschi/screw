package md.dolinschi.screwy.reflection;

import md.dolinschi.screwy.reflection.exception.NoArgsConstructorMissingException;

public class Initializer {

    private Initializer() {
    }

    public static <R> R initialize(final Class<R> aClass) {
        try {
            return aClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new NoArgsConstructorMissingException("The %s class must have default constructor for deep mapping".formatted(aClass.getSimpleName()), e);
        }
    }
}