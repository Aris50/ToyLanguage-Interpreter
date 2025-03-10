package model.types;
import model.values.IValue;
import model.values.RefValue;


public class RefType implements IType{
    private final IType inner;
    public RefType(IType inner){
        this.inner = inner;
    }
    public IType getInner(){
        return inner;
    }
    public boolean equals(Object another){
        if (another instanceof RefType)
            return inner.equals(((RefType) another).getInner());
        return false;
    }

    public String toString(){
        return "Ref(" + inner.toString() + ")";
    }
    public IValue defaultValue(){
        return new RefValue(0, inner);
    }

}
