package model.statements;

import model.state.ProgramState;
import exceptions.*;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;
import model.expressions.IExpression;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import model.adt.IMyMap;
import model.types.IType;



public class OpenRFile implements IStmt {
    private IExpression exp;

    public OpenRFile(IExpression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException {
        IValue value = exp.evaluate(state.getSymTable(), state.getHeap());
        if (!value.getType().equals(new StringType())) {
            throw new StatementException("Expression is not of type StringType.");
        }

        StringValue strVal = (StringValue) value;
        String filename = strVal.getValue();

        if (state.getFileTable().contains(filename)) {
            throw new StatementException("The file already exists.");
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (IOException e) {
            throw new StatementException("Could not open file " + filename + ": " + e.getMessage());
        }

        state.getFileTable().insert(filename, reader);
        return null;
        //return state;
    }

    public IStmt deepCopy() {
        return new OpenRFile(exp.deepCopy());
    }

    @Override
    public String toString() {
        return "openRFile(" + exp.toString() + ")";
    }

    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        IType type = exp.typecheck(typeEnv);
        if (!type.equals(new StringType())) {
            throw new StatementException("Expression is not of type StringType.");
        }
        return typeEnv;
    }
}