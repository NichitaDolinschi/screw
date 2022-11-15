package md.dolinschi.screw.reflection.dummy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DummyReference {
    private int integer;
    private Boolean aBoolean;
    private String string;
    private DummyReference dummyReference;

    private List<Integer> ints = new LinkedList<>(Arrays.asList(1, 2, 34));

    private List<Integer> toSet = new LinkedList<>(Arrays.asList(1, 2, 1, 34));

    private List<DummyInnerReference> dummyInnerReferenceList = List.of(new DummyInnerReference());

    public DummyReference() {
    }

    public List<Integer> getToSet() {
        return toSet;
    }

    public void setToSet(List<Integer> toSet) {
        this.toSet = toSet;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public List<Integer> getInts() {
        return ints;
    }

    public void setInts(List<Integer> ints) {
        this.ints = ints;
    }

    public List<DummyInnerReference> getDummyInnerReferenceList() {
        return dummyInnerReferenceList;
    }

    public void setDummyInnerReferenceList(List<DummyInnerReference> dummyInnerReferenceList) {
        this.dummyInnerReferenceList = dummyInnerReferenceList;
    }

    public DummyReference(int integer, boolean aBoolean, String string, DummyReference dummyReference) {
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

    public DummyReference getDummyReference() {
        return dummyReference;
    }

    public void setDummyReference(DummyReference dummyReference) {
        this.dummyReference = dummyReference;
    }
}
