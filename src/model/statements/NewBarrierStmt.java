package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.adt.IBarrierTable;
import model.adt.IMyHeap;
import model.adt.IMyMap;
import model.adt.Pair;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.types.IntType;
import model.types.IType;
import model.values.IValue;
import model.values.IntValue;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStmt implements IStmt {
    private final String var;
    private final IExpression exp;
    private final Lock lock = new ReentrantLock();

    public NewBarrierStmt(String var, IExpression exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException {
        lock.lock();
        try {
            IMyMap<String, IValue> symTable = state.getSymTable();
            IMyHeap heap = state.getHeap();
            IBarrierTable barrierTable = state.getBarrierTable();

            IValue expValue = exp.evaluate(symTable, heap);
            if (!expValue.getType().equals(new IntType())) {
                throw new StatementException("Expression must evaluate to an integer.");
            }

            int nr = ((IntValue) expValue).getValue();
            int freeAddress = barrierTable.getFreeAddress();
            barrierTable.put(freeAddress, new Pair<>(nr, new ArrayList<>()));

            if (symTable.contains(var) && symTable.getElement(var).getType().equals(new IntType())) {
                symTable.insert(var, new IntValue(freeAddress));
            } else {
                throw new StatementException("Variable is not defined or not of type int.");
            }
        } finally {
            lock.unlock();
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NewBarrierStmt(var, exp.deepCopy());
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        IType varType = typeEnv.getElement(var);
        IType expType = exp.typecheck(typeEnv);

        if (!varType.equals(new IntType())) {
            throw new StatementException("Variable must be of type int.");
        }

        if (!expType.equals(new IntType())) {
            throw new StatementException("Expression must be of type int.");
        }

        return typeEnv;
    }

    @Override
    public String toString() {
        return "newBarrier(" + var + ", " + exp + ")";
    }
}