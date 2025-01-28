package view.interpreter;

public class SymTableRow {
    private final String varName;
    private final String value;

    public SymTableRow(String varName, String value) {
        this.varName = varName;
        this.value = value;
    }

    public String getVarName() {
        return varName;
    }

    public String getValue() {
        return value;
    }
}