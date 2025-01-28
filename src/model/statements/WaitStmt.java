package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.adt.IMyMap;
import model.expressions.IExpression;
import model.expressions.ValueExpression;
import model.state.ProgramState;
import model.types.IType;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;

public class WaitStmt implements IStmt{
    private final IExpression exp;
    public WaitStmt(IExpression exp) {
        this.exp=exp;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        IValue result = exp.evaluate(state.getSymTable(), state.getHeap());
        if (result instanceof IntValue) {
            int number = ((IntValue) result).getValue();
            if (number != 0) {
                state.getExeStack().push(new CompStmt(new PrintStmt(exp), new WaitStmt(new ValueExpression(new IntValue(number - 1)))));
            }
        } else {
            throw new StatementException("Expression is not an integer");
        }
        return null;
    }
    @Override
    public IStmt deepCopy() {
        return new WaitStmt(exp.deepCopy());
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        IType type = exp.typecheck(typeEnv);
        if (type.equals(new IntType())) {
            return typeEnv;
        } else {
            throw new StatementException("The expression of WAIT hasn't got a number as parameter.");
        }
    }

    public String toString(){
        return "wait(" + exp.toString() + ")";
    }
}
