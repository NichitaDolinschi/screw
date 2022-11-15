package md.dolinschi.screw.mapper;

import md.dolinschi.screw.reflection.ObjectField;

import java.util.function.Supplier;

public class SimpleMapper<F, T> implements Mapper<T> {

    private final F reference;
    private final T target;

    public SimpleMapper(final F reference, final T target) {
        if (reference == null || target == null) {
            throw new NullPointerException();
        }
        this.reference = reference;
        this.target = target;
    }

    public SimpleMapper(final F reference, final Supplier<T> target) {
        this(reference, target.get());
    }

    public static <F, T> T mapOf(final F reference, final T target) {
        return new SimpleMapper<>(reference, target).map();
    }

    @Override
    public T map() {
        final var allHierarchyFields = ObjectField.getAllHierarchyFields(reference);
        allHierarchyFields.parallelStream().forEach(referenceField -> {
            try (final var targetField = new ObjectField(target, referenceField.getFieldName(), true)) {
                final var referenceValue = referenceField.getValueAndClose();
                targetField.setValue(referenceValue);
            } catch (final Exception ignored) {
            }
        });
        return target;
    }

}
