package model.statements;

import model.state.ProgramState;
import model.types.IType;
import model.values.IValue;
import exceptions.StatementException;
import exceptions.ExpressionException;
import model.adt.IMyMap;

public class VarDecl implements IStmt {
    private String name;
    private IType type;

    public VarDecl(String name, IType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        if (state.getSymTable().contains(name)) {
            throw new StatementException("Variable " + name + " already declared.");
        }
        IValue defaultValue = type.defaultValue();
        state.getSymTable().insert(name, defaultValue);
        return null;
        //return state;
    }

    public IStmt deepCopy() {
        return new VarDecl(name, type);
    }

    public String toString() {
        return type.toString() + " " + name;
    }

    public IMyMap <String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        typeEnv.insert(name, type);
        return typeEnv;
    }
}