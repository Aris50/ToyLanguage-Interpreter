package model.values;
import model.values.IValue;
import model.types.IType;
import model.types.IntType;


public class IntValue implements IValue{
    private final int value;

    public IntValue(final int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    @Override
    public IType getType(){
        return new IntType();
    }

    @Override
    public boolean equals(Object other){
        return other instanceof IntValue && this.getValue() == ((IntValue)other).getValue();
    }

    public String toString(){
        return String.valueOf(value);
    }

    @Override
    public IValue deepCopy(){
        return new IntValue(value);
    }


}
