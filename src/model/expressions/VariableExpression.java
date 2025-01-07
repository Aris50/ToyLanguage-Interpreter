package model.expressions;

import exceptions.ExpressionException;
import model.adt.IMyMap;
import model.values.IValue;
import model.adt.IMyHeap;
import model.types.IType;

public class VariableExpression implements IExpression {
    private String variable;

    public VariableExpression(String variable) {
        this.variable = variable;
    }

    @Override
    public IValue evaluate(IMyMap<String, IValue> symbTml, IMyHeap heap) throws ExpressionException {
        return symbTml.getElement(variable);
    }

    public String toString() {
        return variable;
    }

    public IExpression deepCopy() {
        return new VariableExpression(variable);
    }

    public IType typecheck(IMyMap<String, IType> typeEnv) throws ExpressionException {
        return typeEnv.getElement(variable);
    }
}
