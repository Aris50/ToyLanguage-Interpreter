package model.expressions;
import exceptions.ExpressionException;
import model.adt.IMyMap;
import model.values.IValue;
import model.adt.IMyHeap;
import model.types.IType;

public interface IExpression {
    IValue evaluate(IMyMap<String, IValue> symbTml, IMyHeap heap) throws ExpressionException;
    IExpression deepCopy();
    IType typecheck(IMyMap <String, IType> typeEnv) throws ExpressionException;
}
