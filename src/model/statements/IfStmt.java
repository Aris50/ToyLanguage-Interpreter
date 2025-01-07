package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.values.BoolValue;
import model.values.IValue;
import model.types.IType;
import model.adt.IMyMap;
import model.types.BoolType;

public class IfStmt implements IStmt {
    private IExpression exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(IExpression exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    public String toString() {
        return "IF(" + exp.toString() + ") THEN {" + thenS.toString() +
                "}ELSE {" + elseS.toString() + "}";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        IValue expVal = this.exp.evaluate(state.getSymTable(), state.getHeap());
        /*
        if (!(expVal.getType() instanceof BoolType)) {
            throw new statementException(expVal.toString() + " is not a boolean");
        }
        */
        if (((BoolValue) expVal).getValue()) {
            state.getExeStack().push(thenS);
        } else {
            state.getExeStack().push(elseS);
        }

        return null;
    }

    public IStmt deepCopy() {
        return new IfStmt(exp.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }

    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        IType typeExp = exp.typecheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            thenS.typecheck(typeEnv);
            elseS.typecheck(typeEnv);
            return typeEnv;
        } else {
            throw new StatementException("The condition of IF has not the type bool.");
        }
    }
}
