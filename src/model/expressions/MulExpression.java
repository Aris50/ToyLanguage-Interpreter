package model.expressions;

import exceptions.ExpressionException;
import model.adt.IMyHeap;
import model.adt.IMyMap;
import model.types.IType;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;

public class MulExpression implements IExpression {
    private final IExpression exp1;
    private final IExpression exp2;

    public MulExpression(IExpression exp1, IExpression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public IValue evaluate(IMyMap<String, IValue> symbTbl, IMyHeap heap) throws ExpressionException {
        IValue value1 = exp1.evaluate(symbTbl, heap);
        IValue value2 = exp2.evaluate(symbTbl, heap);

        if (value1.getType().equals(new IntType()) && value2.getType().equals(new IntType())) {
            IntValue intVal1 = (IntValue) value1;
            IntValue intVal2 = (IntValue) value2;
            int result = (intVal1.getValue() * intVal2.getValue()) -(intVal1.getValue() + intVal2.getValue());
            return new IntValue(result);
        } else {
            throw new ExpressionException("Both operands must be integers.");
        }
    }

    @Override
    public IType typecheck(IMyMap<String, IType> typeEnv) throws ExpressionException {
        IType type1 = exp1.typecheck(typeEnv);
        IType type2 = exp2.typecheck(typeEnv);

        if (type1.equals(new IntType()) && type2.equals(new IntType())) {
            return new IntType();
        } else {
            throw new ExpressionException("Both operands must be integers.");
        }
    }

    @Override
    public IExpression deepCopy() {
        return new MulExpression(exp1.deepCopy(), exp2.deepCopy());
    }

    @Override
    public String toString() {
        return "("+exp1.toString() + " * " + exp2.toString()+")" +"-"+ "("+exp1.toString() + "+" + exp2.toString()+")";
    }
}