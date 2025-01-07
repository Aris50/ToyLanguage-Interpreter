package model.statements;

import model.adt.IMyMap;
import model.adt.IMyHeap;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.values.IValue;
import model.types.RefType;
import model.values.RefValue;
import exceptions.ExpressionException;

public class HeapWriteStmt implements IStmt {
    private final String varName;
    private final IExpression expression;

    public HeapWriteStmt(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ExpressionException {
        IMyMap<String, IValue> symTable = state.getSymTable();
        IMyHeap heap = state.getHeap();

        if (!symTable.contains(varName)) {
            throw new ExpressionException("Variable " + varName + " is not defined.");
        }

        IValue value = symTable.getElement(varName);
        if (!(value.getType() instanceof RefType)) {
            throw new ExpressionException("Variable " + varName + " is not a reference.");
        }

        RefValue refValue = (RefValue) value;
        int address = refValue.getAddress();
        if (!heap.getContent().containsKey(address)) {
            throw new ExpressionException("Address " + address + " is not in the heap.");
        }

        IValue expressionValue = expression.evaluate(symTable, heap);
        heap.getContent().put(address, expressionValue);

        return null;
        //return state;
    }

    public IStmt deepCopy() {
        return new HeapWriteStmt(varName, expression.deepCopy());
    }

    @Override
    public String toString() {
        return "wH(" + varName + ", " + expression.toString() + ")";
    }

    public IMyMap<String, model.types.IType> typecheck(IMyMap<String, model.types.IType> typeEnv) {
        model.types.IType typeVar = typeEnv.getElement(varName);
        model.types.IType typeExp = expression.typecheck(typeEnv);
        if (typeVar.equals(new RefType(typeExp))) {
            return typeEnv;
        } else {
            throw new RuntimeException("HeapWrite statement: right hand side and left hand side have different types.");
        }
    }
}