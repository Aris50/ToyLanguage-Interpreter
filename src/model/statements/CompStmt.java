package model.statements;

import model.state.ProgramState;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.types.IType;
import model.adt.IMyMap;

public class CompStmt implements IStmt{
    private final IStmt first;
    private final IStmt second;

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        state.getExeStack().push(second);
        state.getExeStack().push(first);
        //return state;
        return null;
    }

    @Override
    public String toString() {
        return first.toString() + "; " + second.toString();
    }

    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(), second.deepCopy());
    }

    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        return second.typecheck(first.typecheck(typeEnv));
    }
}
