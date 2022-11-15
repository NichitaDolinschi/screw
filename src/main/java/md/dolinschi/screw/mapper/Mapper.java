package md.dolinschi.screw.mapper;


import java.util.function.Supplier;

public abstract class Mapper<F, T> {
    protected final F reference;
    protected final T target;

    protected Mapper(final F reference, final T target) {
        if (reference == null || target == null) {
            throw new NullPointerException();
        }
        this.reference = reference;
        this.target = target;
    }

    protected Mapper(final F reference, final Supplier<T> target) {
        this(reference, target.get());
    }

    public abstract T map();
}
