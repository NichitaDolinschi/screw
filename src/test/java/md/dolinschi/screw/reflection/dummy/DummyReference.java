package md.dolinschi.screw.reflection.dummy;

public class DummyReference {
    private int integer;
    private Boolean aBoolean;
    private String string;
    private DummyReference dummyReference;

    public DummyReference() {
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
