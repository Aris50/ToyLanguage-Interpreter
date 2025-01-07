package model.expressions;
import exceptions.ExpressionException;
import model.adt.IMyHeap;
import model.values.IValue;
import model.values.IntValue;
import model.adt.IMyMap;
import model.values.RefValue;
import model.types.IType;
import model.types.RefType;

public class HeapReadExpression implements IExpression{
    private IExpression expression;
    public HeapReadExpression(IExpression expression){
        this.expression = expression;
    }
    public IValue evaluate(IMyMap<String, IValue> symbTbl, IMyHeap heap) throws ExpressionException {
        IValue value = expression.evaluate(symbTbl, heap);
        if (!(value instanceof RefValue)) {
            throw new ExpressionException("Expression is not of type RefValue.");
        }
        RefValue refValue = (RefValue) value;
        int address = refValue.getAddress();
        if (!heap.getContent().containsKey(address)) {
            throw new ExpressionException("Address " + address + " is not in the heap.");
        }
        return heap.getContent().get(address);
    }

    public String toString(){
        return "rH(" + expression.toString() + ")";
    }

    public IExpression deepCopy(){
        return new HeapReadExpression(expression.deepCopy());
    }

    public IType typecheck(IMyMap<String, IType> typeEnv) throws ExpressionException {
        IType type = expression.typecheck(typeEnv);
        if (!(type instanceof RefType)) {
            throw new ExpressionException("Expression is not of type RefType.");
        }
        RefType refType = (RefType) type;
        return refType.getInner();
    }
}
