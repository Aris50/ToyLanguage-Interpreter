package model.expressions;
import exceptions.ExpressionException;
import model.adt.IMyMap;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;
import model.adt.IMyHeap;
import model.types.BoolType;
import model.types.IType;

public class RelationalExpression implements IExpression {
    private IExpression exp1;
    private IExpression exp2;
    private int operation;

    /// 1-<, 2-<=, 3-==, 4-!=, 5->, 6->=

    public RelationalExpression(IExpression exp1, IExpression exp2, int operation) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operation = operation;
    }

    @Override
    public IValue evaluate(IMyMap<String, IValue> symbTbl, IMyHeap heap) throws ExpressionException {
        IValue value1, value2;
        value1 = exp1.evaluate(symbTbl,heap);
        if (value1.getType().equals(new IntType())) {
            value2 = exp2.evaluate(symbTbl,heap);
            if (value2.getType().equals(new IntType())) {
                IntValue intVal1 = (IntValue) value1;
                IntValue intVal2 = (IntValue) value2;
                int number1 = intVal1.getValue();
                int number2 = intVal2.getValue();
                switch (operation) {
                    case 1:
                        return new BoolValue(number1 < number2);
                    case 2:
                        return new BoolValue(number1 <= number2);
                    case 3:
                        return new BoolValue(number1 == number2);
                    case 4:
                        return new BoolValue(number1 != number2);
                    case 5:
                        return new BoolValue(number1 > number2);
                    case 6:
                        return new BoolValue(number1 >= number2);
                    default:
                        throw new ExpressionException("Invalid operation");
                }
            } else {
                throw new ExpressionException("Second operand is not an integer");
            }
        } else {
            throw new ExpressionException("First operand is not an integer");
        }
    }

    public String toString() {
        String op = "";
        switch (operation) {
            case 1:
                op = exp1.toString() + "<" + exp2.toString();
                break;
            case 2:
                op = exp1.toString() + "<=" + exp2.toString();
                break;
            case 3:
                op = exp1.toString() + "==" + exp2.toString();
                break;
            case 4:
                op = exp1.toString() + "!=" + exp2.toString();
                break;
            case 5:
                op = exp1.toString() + ">" + exp2.toString();
                break;
            case 6:
                op = exp1.toString() + ">=" + exp2.toString();
                break;
        }
        return op;
    }

    public IExpression deepCopy() {
        return new RelationalExpression(exp1.deepCopy(), exp2.deepCopy(), operation);
    }

    public IType typecheck(IMyMap<String, IType> typeEnv) throws ExpressionException {
        var type1 = exp1.typecheck(typeEnv);
        var type2 = exp2.typecheck(typeEnv);
        if (!type1.equals(new IntType()) || !type2.equals(new IntType())) {
            throw new ExpressionException("Left and right expressions are not the same");
        }
        return new BoolType();
    }
}