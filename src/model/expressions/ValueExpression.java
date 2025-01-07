package model.expressions;

import model.adt.IMyMap;
import model.values.IValue;
import model.adt.IMyHeap;
import model.types.IType;

public class ValueExpression implements IExpression {
    private IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(IMyMap<String, IValue> symbTml, IMyHeap heap) {
        return value;
    }

    public String toString() {
        return value.toString();
    }

    public IExpression deepCopy() {
        return new ValueExpression(value.deepCopy());
    }

    public IType typecheck(IMyMap<String, IType> typeEnv) {
        return value.getType();
    }
}
