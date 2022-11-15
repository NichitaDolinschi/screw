package md.dolinschi.screw.reflection.dummy;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class DummyTarget {
    private int integer;
    private boolean aBoolean;
    private String string;
    private DummyTarget dummyReference;

    private List<Integer> ints;

    private List<DummyInnerTarget> dummyInnerReferenceList;

    private Set<Integer> toSet;

    public Set<Integer> getToSet() {
        return toSet;
    }

    public void setToSet(Set<Integer> toSet) {
        this.toSet = toSet;
    }

    public DummyTarget() {
    }

    public List<Integer> getInts() {
        return ints;
    }

    public void setInts(List<Integer> ints) {
        this.ints = ints;
    }

    public List<DummyInnerTarget> getDummyInnerReferenceList() {
        return dummyInnerReferenceList;
    }

    public void setDummyInnerReferenceList(List<DummyInnerTarget> dummyInnerReferenceList) {
        this.dummyInnerReferenceList = dummyInnerReferenceList;
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
