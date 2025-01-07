package model.types;
import model.values.IValue;
import model.values.IntValue;

public class IntType implements IType{
    @Override
    public boolean equals(Object other) {
        return other instanceof IntType;
    }

    public String toString() {
        return "Int";
    }

    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }
}
