package model.values;

import model.values.IValue;
import model.types.IType;
import model.types.BoolType;

public class BoolValue implements IValue {
    private final boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BoolValue && this.getValue() == ((BoolValue)other).getValue();
    }

    public String toString() {
        return String.valueOf(value);
    }


    public IValue deepCopy() {
        return new BoolValue(value);
    }
}