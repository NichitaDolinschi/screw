package md.dolinschi.screw.mapper;

import md.dolinschi.screw.reflection.ObjectField;

import java.util.function.Supplier;

public class SimpleMapper<F, T> extends Mapper<F, T> {

    public SimpleMapper(F reference, T target) {
        super(reference, target);
    }

    public SimpleMapper(F reference, Supplier<T> target) {
        super(reference, target);
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
