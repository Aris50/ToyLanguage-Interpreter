package model.types;
import model.values.BoolValue;
import model.values.IValue;

public class BoolType implements IType {
    public boolean equals(Object other){
        return other instanceof BoolType;
    }
    @Override
    public IValue defaultValue(){
        return new BoolValue(false);
    }
    public String toString(){
        return "Boolean";
    }
}
