package model.statements;

import model.state.ProgramState;
import exceptions.StatementException;
import exceptions.ExpressionException;
import model.adt.IMyMap;
import model.types.IType;

public class NopStmt implements IStmt {

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        // No operation is performed, just return the current state
        return null;
        //return state;
    }

    public IStmt deepCopy() {
        return new NopStmt();
    }

    public String toString() {
        return "nop";
    }

    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        return typeEnv;
    }
}