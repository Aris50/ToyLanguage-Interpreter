package model.expressions;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.adt.IMyHeap;
import model.adt.IMyMap;
import model.types.BoolType;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;

public class NotExpression implements IExpression{
    private final IExpression exp;
    public NotExpression(IExpression exp){
        this.exp=exp;
    }
    @Override
    public IValue evaluate(IMyMap<String, IValue> symbTml, IMyHeap heap) throws ExpressionException {
        IValue expVal = exp.evaluate(symbTml, heap);
        if (!(expVal.getType() instanceof BoolType)) {
            throw new StatementException(expVal.toString() + "is not a boolean");
        }
        BoolValue boolVal = (BoolValue) expVal;
        return new BoolValue(!boolVal.getValue());
    }

    @Override
    public IExpression deepCopy() {
        return new NotExpression(exp.deepCopy());
    }

    @Override
    public IType typecheck(IMyMap<String, IType> typeEnv) throws ExpressionException {
        IType typeExp = exp.typecheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            return new BoolType();
        } else {
            throw new ExpressionException("The condition of NOT has not the type bool.");
        }
    }

    public String toString(){
        return "!(" + exp.toString() + ")";
    }
}
