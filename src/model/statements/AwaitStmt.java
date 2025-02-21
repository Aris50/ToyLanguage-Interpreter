package model.statements;

import exceptions.StatementException;
import model.adt.IBarrierTable;
import model.adt.IMyHeap;
import model.adt.IMyMap;
import model.adt.Pair;
import model.state.ProgramState;
import model.types.IType;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitStmt implements IStmt {
    private final String var;
    private final Lock lock = new ReentrantLock();

    public AwaitStmt(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException {
        lock.lock();
        try {
            IMyMap<String, IValue> symTable = state.getSymTable();
            IBarrierTable barrierTable = state.getBarrierTable();
            IMyHeap heap = state.getHeap();

            if (!symTable.contains(var) || !symTable.getElement(var).getType().equals(new IntType())) {
                throw new StatementException("Variable is not defined or not of type int.");
            }

            int foundIndex = ((IntValue) symTable.getElement(var)).getValue();

            if (!barrierTable.containsKey(foundIndex)) {
                throw new StatementException("Index not found in BarrierTable.");
            }

            Pair<Integer, List<Integer>> barrierEntry = barrierTable.get(foundIndex);
            int N1 = barrierEntry.getFirst();
            List<Integer> L1 = barrierEntry.getSecond();
            int NL = L1.size();

            if (N1 > NL) {
                if (!L1.contains(state.getId())) {
                    L1.add(state.getId());
                }
                state.getExeStack().push(this);
            }
        } finally {
            lock.unlock();
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new AwaitStmt(var);
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        IType varType = typeEnv.getElement(var);

        if (!varType.equals(new IntType())) {
            throw new StatementException("Variable must be of type int.");
        }

        return typeEnv;
    }

    @Override
    public String toString() {
        return "await(" + var + ")";
    }
}