package md.dolinschi.screwy.mapper;

import md.dolinschi.screwy.reflection.Initializer;
import md.dolinschi.screwy.reflection.ObjectField;
import org.apache.commons.lang3.ClassUtils;

import java.util.function.Supplier;

public class DeepMapper<F, T> implements Mapper<T> {

    private final F reference;
    private final T target;

    public DeepMapper(final F reference, final T target) {
        if (reference == null || target == null) {
            throw new NullPointerException();
        }
        this.reference = reference;
        this.target = target;
    }

    public DeepMapper(final F reference, final Supplier<T> target) {
        this(reference, target.get());
    }

    public static <F, T> T mapOf(final F reference, final T target) {
        return new DeepMapper<>(reference, target).map();
    }

    @Override
    public T map() {
        final var allHierarchyFields = ObjectField.getAllHierarchyFields(reference);
        allHierarchyFields.parallelStream().forEach(referenceField -> {
            try (final var targetField = new ObjectField(target, referenceField.getFieldName(), true)) {
                if (ClassUtils.isPrimitiveOrWrapper(reference.getClass())
                        || ClassUtils.isPrimitiveOrWrapper(targetField.getFieldClass())
                        || referenceField.isAssignableFrom(targetField)) {
                    final var referenceValue = referenceField.getValueAndClose();
                    targetField.setValue(referenceValue);
                } else if (!referenceField.isAssignableFrom(targetField) && !referenceField.getFieldClass().isEnum()) {
                    final var referenceValue = referenceField.getValueAndClose();
                    final var targetObject = Initializer.initialize(targetField.getFieldClass());
                    if (referenceValue != null) {
                        final var innerMapper = new DeepMapper<>(referenceValue, targetObject);
                        targetField.setValue(innerMapper.map());
                    }
                }
            } catch (final Exception ignored) {
            }
        });
        return target;
    }

}
