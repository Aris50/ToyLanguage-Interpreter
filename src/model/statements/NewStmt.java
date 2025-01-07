package model.statements;
import model.types.IType;
import model.values.IValue;

import model.adt.IMyMap;
import model.adt.IMyStack;
import exceptions.NullRefferenceException;
import model.adt.IMyHeap;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.values.RefValue;
import model.types.RefType;


public class NewStmt implements IStmt{
    private final String varName;
    private final IExpression expression;

    public NewStmt(String varName, IExpression expression){
        this.varName = varName;
        this.expression = expression;
    }

    public String toString(){
        return "new(" + varName + ", " + expression.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws NullRefferenceException {
        IValue val = expression.evaluate(state.getSymTable(), state.getHeap());
        if(!state.getSymTable().contains(varName)){
            throw new NullRefferenceException("Variable " + varName + " is not defined.");
        }
        IValue ref = state.getSymTable().getElement(varName);
        if (!ref.getType().equals(new RefType(val.getType()))) {
            throw new NullRefferenceException("Variable " + varName + " is not a ref to " + val.getType());
        }

        int addr = state.getHeap().allocate(val);
        var type = ((RefType) ref.getType()).getInner();
        state.getSymTable().insert(varName, new RefValue(addr, type));

        return null;
        //return state;
    }

    public IStmt deepCopy() {
        return new NewStmt(varName, expression.deepCopy());
    }

    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) {
        IType typeVar = typeEnv.getElement(varName);
        IType typeExp = expression.typecheck(typeEnv);
        if (typeVar.equals(new RefType(typeExp))) {
            return typeEnv;
        } else {
            throw new RuntimeException("NEW statement: right hand side and left hand side have different types.");
        }
    }
}

