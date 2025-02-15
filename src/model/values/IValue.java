package model.values;
import model.types.IType;

public interface IValue {
    IType getType();
    boolean equals(Object other);
    IValue deepCopy();
}
