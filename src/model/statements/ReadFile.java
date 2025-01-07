package model.statements;

import model.state.ProgramState;
import exceptions.*;
import model.types.IntType;
import model.types.StringType;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;
import model.expressions.IExpression;
import java.io.BufferedReader;
import java.io.IOException;
import model.adt.IMyMap;

public class ReadFile implements IStmt {
    private IExpression exp;
    private String varName;

    public ReadFile(IExpression exp, String varName) {
        this.exp = exp;
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        // Check if varName is defined in SymTable and its type is int
        if (!state.getSymTable().contains(varName)) {
            throw new StatementException("Variable " + varName + " is not defined in the symbol table.");
        }
        IValue varValue = state.getSymTable().getElement(varName);
        if (!varValue.getType().equals(new IntType())) {
            throw new StatementException("Variable " + varName + " is not of type int.");
        }

        // Evaluate exp to a value that must be a string value
        IValue value = exp.evaluate(state.getSymTable(), state.getHeap());
        if (!value.getType().equals(new StringType())) {
            throw new StatementException("Expression is not of type StringType.");
        }

        StringValue strVal = (StringValue) value;
        String filename = strVal.getValue();


        if (!state.getFileTable().contains(filename)) {
            throw new StatementException("The file does not exist.");
        }

        BufferedReader reader = state.getFileTable().getElement(filename);
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new StatementException("Could not read from file " + filename + ": " + e.getMessage());
        }

        // Read a line from the file and convert to int
        int intValue;
        if (line == null) {
            intValue = 0;
        } else {
            try {
                intValue = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                throw new StatementException("Could not convert line to int: " + e.getMessage());
            }
        }

        // Update SymTable such that varName is mapped to the int value
        state.getSymTable().insert(varName, new IntValue(intValue));
        return null;
        //return state;
    }

    public IStmt deepCopy() {
        return new ReadFile(exp.deepCopy(), varName);
    }

    @Override
    public String toString() {
        return "readFile(" + exp.toString() + ", " + varName + ")";
    }

    public IMyMap<String, model.types.IType> typecheck(IMyMap<String, model.types.IType> typeEnv) throws StatementException {
        model.types.IType type = exp.typecheck(typeEnv);
        if (!type.equals(new StringType())) {
            throw new StatementException("Expression is not of type StringType.");
        }
        return typeEnv;
    }
}