package model.state;

import java.io.BufferedReader;
import model.adt.*;
import model.statements.*;
import model.values.IValue;
import exceptions.*;

public class ProgramState {
    private IMyStack<IStmt> exeStack;
    private IMyMap<String, IValue> symTable;
    private IMyList<IValue> out;
    private IMyMap<String, BufferedReader> fileTable;
    private IMyHeap heap;
    private IBarrierTable barrierTable;
    private IStmt originalProgram;
    private int id;
    private static int nextId = 0;

    public ProgramState(IMyStack<IStmt> stk, IMyMap<String, IValue> symtbl, IMyList<IValue> out, IStmt prg,
                        IMyMap<String, BufferedReader> fMap, IMyHeap heap, IBarrierTable barrierTable) {
        this.exeStack = stk;
        this.symTable = symtbl;
        this.out = out;
        this.originalProgram = prg.deepCopy();
        this.fileTable = fMap;
        this.heap = heap;
        this.barrierTable = barrierTable;
        this.id = getNextId();
        exeStack.push(prg.deepCopy());
    }

    public IMyStack<IStmt> getExeStack() {
        return this.exeStack;
    }

    public IMyMap<String, IValue> getSymTable() {
        return this.symTable;
    }

    public IMyList<IValue> getOut() {
        return this.out;
    }

    public IMyMap<String, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public IMyHeap getHeap() {
        return this.heap;
    }

    public IBarrierTable getBarrierTable() {
        return this.barrierTable;
    }

    private static synchronized int getNextId() {
        return nextId++;
    }

    public ProgramState oneStep() throws ExpressionException {
        if (exeStack.isEmpty()) {
            throw new ExpressionException("Execution stack is empty");
        }
        IStmt currentStmt = exeStack.pop();
        return currentStmt.execute(this);
    }

    public boolean isNotCompleted() {
        return !(this.exeStack.isEmpty());
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "ProgramState: " + "\n" +
                "ID: " + id + "\n" +
                "STACK: \n" + exeStack + "\n" +
                "TABLE: " + symTable + "\n" +
                "OUTPUT: " + out + "\n-------------------------------------\n" +
                "FILE TABLE: " + fileTable + "\n-------------------------------------\n" +
                "HEAP: " + heap + "\n-------------------------------------\n" +
                "BARRIER TABLE: " + barrierTable + "\n-------------------------------------\n";
    }
}