package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.adt.IMyMap;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.types.BoolType;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;
import model.expressions.NotExpression;

public class RepeatStmt implements IStmt{
    private final IStmt stmt1;
    private final IExpression exp2;

    public RepeatStmt(IStmt stmt1, IExpression exp2){
        this.stmt1=stmt1;
        this.exp2=exp2;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        IValue expVal=this.exp2.evaluate(state.getSymTable(), state.getHeap());
        if (!(expVal.getType() instanceof BoolType)) {
            throw new StatementException(expVal.toString() + " is not a boolean");
        }
        if (!(((BoolValue) expVal).getValue())) {  ///If the value is FALSE
            IStmt toPush=new CompStmt(stmt1, new WhileStmt(new NotExpression(exp2), stmt1));
            state.getExeStack().push(toPush);
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new RepeatStmt(stmt1.deepCopy(), exp2.deepCopy());
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        IType typeExp = exp2.typecheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            stmt1.typecheck(typeEnv);
            return typeEnv;
        } else {
            throw new StatementException("The condition of REPEAT has not the type bool.");
        }
    }

    public String toString(){
        return "( repeat (" + stmt1.toString() + ") until " + "(" + exp2.toString() + "))";
    }
}
