package model.statements;

import model.state.ProgramState;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.types.IType;
import model.adt.IMyMap;


public interface IStmt {
    ProgramState execute(ProgramState state) throws StatementException, ExpressionException;
    IStmt deepCopy();
    IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException;
}
