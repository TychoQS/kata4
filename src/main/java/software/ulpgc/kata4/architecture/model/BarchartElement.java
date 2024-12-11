package software.ulpgc.kata4.architecture.model;

public class BarchartElement {
    private final String field;
    private final int value;

    public BarchartElement(String field, int value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public int getValue() {
        return value;
    }
}
