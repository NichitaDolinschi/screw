package md.dolinschi.screw.reflection;

import md.dolinschi.screw.reflection.exception.NoArgsConstructorMissingException;

public class Initializer {

    private Initializer() {
    }

    public static <R> R initializeObject(final Class<R> aClass) {
        try {
            return aClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new NoArgsConstructorMissingException("The %s class must have default constructor for deep mapping".formatted(aClass.getSimpleName()), e);
        }
    }

    public static <R> R initializeCollection(final Class<R> aClass) {
        try {
            return aClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new NoArgsConstructorMissingException("The %s class must have default constructor for deep mapping".formatted(aClass.getSimpleName()), e);
        }
    }
}
