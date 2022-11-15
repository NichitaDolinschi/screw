package md.dolinschi.screw.mapper;

import md.dolinschi.screw.reflection.Initializer;
import md.dolinschi.screw.reflection.ObjectField;
import md.dolinschi.screw.reflection.exception.CollectionNotCompatibleType;
import org.apache.commons.lang3.ClassUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DeepMapper<F, T> extends Mapper<F, T> {


    public DeepMapper(F reference, T target) {
        super(reference, target);
    }

    public DeepMapper(F reference, Supplier<T> target) {
        super(reference, target);
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
                    if (referenceField.isCollection()) {
                        if (referenceField.getGenericClass().equals(targetField.getGenericClass())) {
                            //do copy
                            final var referenceValue = referenceField.getValueAndClose();
                            targetField.setValue(referenceValue);
                        } else {
                            mapCollection(referenceField, targetField);
                        }
                    } else {
                        final var referenceValue = referenceField.getValueAndClose();
                        targetField.setValue(referenceValue);
                    }
                } else if (!referenceField.isAssignableFrom(targetField) && !referenceField.getFieldClass().isEnum()) {
                    if (referenceField.isCollection()) {
                        mapCollection(referenceField, targetField);
                    } else {
                        final var referenceValue = referenceField.getValueAndClose();
                        final var targetObject = Initializer.initializeObject(targetField.getFieldClass());
                        if (referenceValue != null) {
                            final var innerMapper = new DeepMapper<>(referenceValue, targetObject);
                            targetField.setValue(innerMapper.map());
                        }
                    }
                }
            } catch (final CollectionNotCompatibleType collectionNotCompatibleType) {
                throw collectionNotCompatibleType;
            } catch (final Exception ignored) {
            }
        });
        return target;
    }

    private void mapCollection(final ObjectField referenceField, final ObjectField targetField) {
        try {
            if (referenceField.getValue() instanceof Collection<?> referenceCollection) {
                if (targetField.isSubTypeOrEquals(List.class)) {
                    targetField.setValue(ClassUtils.isPrimitiveOrWrapper(referenceField.getGenericClass())
                            ? referenceCollection.parallelStream().toList()
                            : referenceCollection.parallelStream().map(referenceValue -> DeepMapper.mapOf(referenceValue, Initializer.initializeObject(targetField.getGenericClass())))
                            .toList());
                } else if (targetField.isSubTypeOrEquals(Set.class)) {
                    targetField.setValue(ClassUtils.isPrimitiveOrWrapper(referenceField.getGenericClass())
                            ? referenceCollection.parallelStream().collect(Collectors.toSet())
                            : referenceCollection.parallelStream().map(referenceValue -> DeepMapper.mapOf(referenceValue, Initializer.initializeObject(targetField.getGenericClass())))
                            .collect(Collectors.toSet()));
                }
                referenceField.close();
            }
        } catch (final Exception exception) {
            throw new CollectionNotCompatibleType(exception);
        }
    }
}
