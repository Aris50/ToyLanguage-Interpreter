package model.values;
import model.types.IType;
import model.types.RefType;

public class RefValue implements IValue{
    private final int address;
    private final IType locationType;

    public RefValue(int address, IType locationType){
        this.address = address;
        this.locationType = locationType;
    }

    public boolean equals(Object other){
        return other instanceof RefValue && this.address == ((RefValue)other).getAddress() && this.locationType.equals(((RefValue)other).getType());
    }

    public String toString(){
        return "(" + address + ", " + locationType.toString() + ")";
    }
    public int getAddress(){
        return address;
    }

    public IType getType(){
        return new RefType(locationType);
    }

    @Override
    public IValue deepCopy(){
        return new RefValue(address, locationType);
    }

}
