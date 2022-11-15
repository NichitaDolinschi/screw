package md.dolinschi.screw.reflection.dummy;

import java.util.Objects;

public class DummyTarget {
    private int integer;
    private boolean aBoolean;
    private String string;
    private DummyTarget dummyReference;

    public DummyTarget() {
    }

    public DummyTarget(int integer, boolean aBoolean, String string, DummyTarget dummyReference) {
        this.integer = integer;
        this.aBoolean = aBoolean;
        this.string = string;
        this.dummyReference = dummyReference;
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public DummyTarget getDummyReference() {
        return dummyReference;
    }

    public void setDummyReference(DummyTarget dummyReference) {
        this.dummyReference = dummyReference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DummyTarget that)) return false;

        if (integer != that.integer) return false;
        if (aBoolean != that.aBoolean) return false;
        if (!Objects.equals(string, that.string)) return false;
        return Objects.equals(dummyReference, that.dummyReference);
    }

    @Override
    public int hashCode() {
        int result = integer;
        result = 31 * result + (aBoolean ? 1 : 0);
        result = 31 * result + (string != null ? string.hashCode() : 0);
        result = 31 * result + (dummyReference != null ? dummyReference.hashCode() : 0);
        return result;
    }
}
