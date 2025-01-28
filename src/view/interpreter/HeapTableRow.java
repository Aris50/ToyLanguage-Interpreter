package view.interpreter;

public class HeapTableRow {
    private final int address;
    private final String value;

    public HeapTableRow(int address, String value) {
        this.address = address;
        this.value = value;
    }

    public int getAddress() {
        return address;
    }

    public String getValue() {
        return value;
    }
}