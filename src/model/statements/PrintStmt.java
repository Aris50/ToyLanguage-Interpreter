package model.statements;

import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.values.IValue;
import model.types.IType;
import model.adt.IMyMap;

public class PrintStmt implements IStmt {
    private IExpression expression;

    public PrintStmt(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException {
        IValue value = expression.evaluate(state.getSymTable(), state.getHeap());
        state.getOut().add(value);
        return null;
        //return state;
    }

    public IStmt deepCopy() {
        return new PrintStmt(expression.deepCopy());
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        expression.typecheck(typeEnv);
        return typeEnv;
    }


}
