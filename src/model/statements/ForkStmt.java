package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.statements.IStmt;
import model.state.ProgramState;
import model.adt.IMyStack;
import model.adt.IMyMap;
import model.adt.IMyList;
import model.adt.IMyHeap;
import java.io.BufferedReader;
import model.values.IValue;
import model.adt.MyStack;
import model.adt.IMyMap;
import model.types.IType;

public class ForkStmt implements IStmt {
    private final IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        IMyStack<IStmt> newExeStack = new MyStack<>();
       // newExeStack.push(stmt);

        IMyMap<String, IValue> newSymTable = state.getSymTable().deepCopy();
        IMyList<IValue> out = state.getOut();
        IMyMap<String, BufferedReader> fileTable = state.getFileTable();
        IMyHeap heap = state.getHeap();

        return new ProgramState(newExeStack, newSymTable, out, stmt.deepCopy(), fileTable, heap);
    }

    @Override
    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }

    public IStmt deepCopy() {
        return new ForkStmt(stmt.deepCopy());
    }

    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        stmt.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }
}