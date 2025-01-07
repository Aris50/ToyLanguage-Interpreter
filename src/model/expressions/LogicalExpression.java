package model.expressions;

import exceptions.ExpressionException;
import model.adt.IMyMap;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;
import model.adt.IMyHeap;
import model.types.IType;

public class LogicalExpression implements IExpression {
    private IExpression expression1;
    private IExpression expression2;
    private LogicalOperation operation;

    public LogicalExpression(IExpression expression1, IExpression expression2, LogicalOperation operation) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }

    public String toString() {
        return expression1.toString() + " " + operation.toString().toLowerCase() + " " + expression2.toString();
    }

    @Override
    public IValue evaluate(IMyMap<String, IValue> symTable, IMyHeap heap) throws ExpressionException {
        var leftExpression = expression1.evaluate(symTable,heap);
        var rightExpression = expression2.evaluate(symTable,heap);

        if (!leftExpression.getType().equals(new BoolType()) || !rightExpression.getType().equals(new BoolType())) {
            throw new ExpressionException("Left and right expressions are not the same");
        }
        if (operation == LogicalOperation.AND) {
            return new BoolValue(((BoolValue) leftExpression).getValue() && ((BoolValue) rightExpression).getValue());
        } else {
            return new BoolValue(((BoolValue) leftExpression).getValue() && ((BoolValue) rightExpression).getValue());
        }

    }

    public IExpression deepCopy() {
        return new LogicalExpression(expression1.deepCopy(), expression2.deepCopy(), operation);
    }

    public IType typecheck(IMyMap<String, IType> typeEnv) throws ExpressionException {
        var type1 = expression1.typecheck(typeEnv);
        var type2 = expression2.typecheck(typeEnv);
        if (!type1.equals(new BoolType()) || !type2.equals(new BoolType())) {
            throw new ExpressionException("Left and right expressions are not the same");
        }
        return new BoolType();
    }


}
