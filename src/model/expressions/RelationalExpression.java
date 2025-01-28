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
    private final IExpression exp1;
    private final IExpression exp2;
    private final int operation;

    /// 1-<, 2-<=, 3-==, 4-!=, 5->, 6->=

    public RelationalExpression(IExpression exp1, IExpression exp2, int operation) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operation = operation;
    }

    public RelationalExpression(IExpression exp1){
        this.exp1=exp1;
        this.exp2=null;
        this.operation=-1;
    }

    @Override
    public IValue evaluate(IMyMap<String, IValue> symbTbl, IMyHeap heap) throws ExpressionException {
        IValue value1, value2;
        value1 = exp1.evaluate(symbTbl, heap);
        if(value1.getType().equals(new BoolType()) && exp2==null)
            return value1;
        if (value1.getType().equals(new IntType())) {
            if (exp2 == null) {
                IntValue intVal1 = (IntValue) value1;
                int number1 = intVal1.getValue();
                return new BoolValue(number1 != 0);
            } else {
                value2 = exp2.evaluate(symbTbl, heap);
                if (value2.getType().equals(new IntType())) {
                    IntValue intVal1 = (IntValue) value1;
                    IntValue intVal2 = (IntValue) value2;
                    int number1 = intVal1.getValue();
                    int number2 = intVal2.getValue();
                    return switch (operation) {
                        case 1 -> new BoolValue(number1 < number2);
                        case 2 -> new BoolValue(number1 <= number2);
                        case 3 -> new BoolValue(number1 == number2);
                        case 4 -> new BoolValue(number1 != number2);
                        case 5 -> new BoolValue(number1 > number2);
                        case 6 -> new BoolValue(number1 >= number2);
                        default -> throw new ExpressionException("Invalid operation");
                    };
                }
                else{
                    throw new ExpressionException("Second operand is not an integer");}
            }
        }
        else {
            throw new ExpressionException("First operand is not an integer");
        }
    }

    public String toString() {
        String op = switch (operation) {
            case 1 -> "<";
            case 2 -> "<=";
            case 3 -> "==";
            case 4 -> "!=";
            case 5 -> ">";
            case 6 -> ">=";
            default -> "";
        };
        return exp1.toString() + op + (exp2 != null ? exp2.toString() : "");
    }

    public IExpression deepCopy() {
        return new RelationalExpression(exp1.deepCopy(), exp2 != null ? exp2.deepCopy() : null, operation);
    }

    public IType typecheck(IMyMap<String, IType> typeEnv) throws ExpressionException {
        if (exp1 == null) {
            throw new ExpressionException("First operand is null");
        }

        IType type1 = exp1.typecheck(typeEnv);
        if (!type1.equals(new IntType())) {
            throw new ExpressionException("First operand is not an integer");
        }

        if (exp2 != null) {
            IType type2 = exp2.typecheck(typeEnv);
            if (!type2.equals(new IntType())) {
                throw new ExpressionException("Second operand is not an integer");
            }
        }

        return new BoolType();
    }

}