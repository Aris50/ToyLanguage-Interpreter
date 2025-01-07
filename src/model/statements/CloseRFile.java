package model.statements;

import model.state.ProgramState;
import exceptions.*;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;
import model.expressions.IExpression;
import java.io.BufferedReader;
import java.io.IOException;
import model.adt.IMyMap;


public class CloseRFile implements IStmt {
    private IExpression exp;

    public CloseRFile(IExpression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        // Evaluate exp to a value that must be a string value
        IValue value = exp.evaluate(state.getSymTable(), state.getHeap());
        if (!value.getType().equals(new StringType())) {
            throw new StatementException("Expression is not of type StringType.");
        }

        StringValue strVal = (StringValue) value;
        String filename = strVal.getValue();

        // Get the BufferedReader object associated in the FileTable
        if (!state.getFileTable().contains(filename)) {
            throw new StatementException("The file does not exist in the FileTable.");
        }

        BufferedReader reader = state.getFileTable().getElement(filename);
        try {
            // Call the close method of the BufferedReader object
            reader.close();
        } catch (IOException e) {
            throw new StatementException("Could not close file " + filename + ": " + e.getMessage());
        }

        // Delete the entry from the FileTable
        state.getFileTable().remove(filename);
        //return state;
        return null;
    }

    public IStmt deepCopy() {
        return new CloseRFile(exp.deepCopy());
    }

    @Override
    public String toString() {
        return "closeRFile(" + exp.toString() + ")";
    }

    public IMyMap<String, model.types.IType> typecheck(IMyMap<String, model.types.IType> typeEnv) throws StatementException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}