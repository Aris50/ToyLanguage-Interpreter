package model.values;
import model.types.IType;
import model.values.IValue;
import model.types.StringType;


public class StringValue implements IValue{
    private String val;
    public StringValue(String value){
        this.val = value;
    }
    public String getValue(){
        return val;
    }
    @Override
    public boolean equals(Object other){
        return other instanceof StringValue && this.getValue().equals(((StringValue)other).getValue());
    }
    @Override
    public IType getType(){
        return new StringType();
    }
    public String toString(){
        return val;
    }

    @Override
    public IValue deepCopy(){
        return new StringValue(val);
    }


}
