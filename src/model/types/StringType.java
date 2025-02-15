package model.types;

import model.values.IValue;
import model.values.StringValue;

public class StringType implements IType {
    @Override
    public boolean equals(Object other) {
        return other instanceof StringType;
    }

    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }

    public String toString() {
        return "string";
    }

}
