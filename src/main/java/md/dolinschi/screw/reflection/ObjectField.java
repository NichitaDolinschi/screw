package md.dolinschi.screw.reflection;

import md.dolinschi.screw.reflection.exception.FieldNotFoundException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;


public class ObjectField implements AutoCloseable {
    private final Object object;
    private final Field field;
    private final Class<?> objectClass;

    public ObjectField(Object object, String fieldName, boolean hierarchyField) {
        this.object = object;
        this.objectClass = object.getClass();
        this.field = hierarchyField ? getHierarchyField(object, fieldName) : getField(object, fieldName);
        if (this.field == null) {
            throw new FieldNotFoundException("Field %s wasn't found".formatted(fieldName));
        }
        this.field.setAccessible(true);
    }

    public ObjectField(Object object, Field field) {
        this.object = object;
        this.objectClass = object.getClass();
        this.field = field;
        if (this.field == null) {
            throw new FieldNotFoundException();
        }
        this.field.setAccessible(true);
    }

    public Object getValue() {
        try {
            return field.get(object);
        } catch (final IllegalAccessException ignored) {
            return null;
        }
    }

    public Object getValue(UnaryOperator<Object> beforeGet) {
        try {
            return beforeGet.apply(field.get(object));
        } catch (final IllegalAccessException ignored) {
            return null;
        }
    }

    public Object setValue(Object value) {
        try {
            field.set(object, value);
            return value;
        } catch (final IllegalAccessException ignored) {
            return value;
        }
    }

    public Object setValue(Object value, UnaryOperator<Object> beforeSet) {
        try {
            field.set(object, beforeSet.apply(value));
            return value;
        } catch (final IllegalAccessException ignored) {
            return value;
        }
    }

    public Object getValueAndClose() {
        final var fieldValue = getValue();
        field.setAccessible(false);
        return fieldValue;
    }

    public Object getValueAndClose(UnaryOperator<Object> beforeGet) {
        final var fieldValue = getValue(beforeGet);
        field.setAccessible(false);
        return fieldValue;
    }

    public Object setValueAndClose(Object value) {
        final var fieldValue = setValue(value);
        field.setAccessible(false);
        return fieldValue;
    }

    public Object setValueAndClose(Object value, UnaryOperator<Object> beforeSet) {
        final var fieldValue = setValue(value, beforeSet);
        field.setAccessible(false);
        return fieldValue;
    }


    public boolean isAssignableFrom(ObjectField target) {
        return getFieldClass().equals(target.getFieldClass());
    }

    public Class<?> getObjectClass() {
        return objectClass;
    }

    public Class<?> getFieldClass() {
        return field.getType();
    }

    public String getFieldName() {
        return field.getName();
    }

    public boolean isClosed() {
        return field.canAccess(object);
    }

    public static Field getField(final Object obj, final String fieldName) {
        final var aClass = obj.getClass();
        try {
            final var field = aClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (final NoSuchFieldException ignored) {
            return null;
        }
    }

    public static Field getHierarchyField(final Object target, final String name) {
        Class<?> clazz = target.getClass();
        Field field = null;
        while (clazz != null && field == null) {
            try {
                field = clazz.getDeclaredField(name);
                field.setAccessible(true);
            } catch (final Exception ignored) {
                field = null;
            }
            clazz = clazz.getSuperclass();
        }
        return field;
    }

    public static List<ObjectField> getFields(final Object object) {
        return Arrays.asList(object.getClass().getDeclaredFields())
                .parallelStream()
                .map(field -> new ObjectField(object, field))
                .toList();
    }

    public static List<ObjectField> getAllHierarchyFields(final Object object) {
        var aClass = object.getClass();
        final var fields = new ArrayList<Field>(10);
        do {
            Collections.addAll(fields, aClass.getDeclaredFields());
            aClass = aClass.getSuperclass();
        } while (aClass != null);
        return fields.stream()
                .map(field -> new ObjectField(object, field))
                .toList();
    }

    @Override
    public void close() {
        this.field.setAccessible(false);
    }
}
