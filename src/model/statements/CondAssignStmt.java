package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.adt.IMyMap;
import model.expressions.IExpression;
import model.expressions.RelationalExpression;
import model.expressions.VariableExpression;
import model.state.ProgramState;
import model.types.IType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;

public class CondAssignStmt implements IStmt{
    private final String variable;
    private final IExpression exp1;
    private final IExpression exp2;
    private final IExpression exp3;
    public CondAssignStmt(String variable, IExpression exp1, IExpression exp2, IExpression exp3){
        this.variable=variable;
        this.exp1=exp1;
        this.exp2=exp2;
        this.exp3=exp3;
    };
    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        if (!state.getSymTable().contains(this.variable)) {
            throw new ExpressionException("The variable is not defined.");
        }
        IValue r2=exp2.evaluate(state.getSymTable(), state.getHeap());
        IValue r3=exp3.evaluate(state.getSymTable(), state.getHeap());
        IType type = state.getSymTable().getElement(variable).getType();
        if(r2.getType().equals(type) && r3.getType().equals(type)){
            state.getExeStack().push(new IfStmt(new RelationalExpression(exp1), new AssignStmt(variable, exp2),new AssignStmt(variable, exp3)));
            return null;
        }
        else{
            throw new StatementException("Values do not have similar types.");
        }
    }

    @Override
    public IStmt deepCopy() {
        return new CondAssignStmt(variable,exp1.deepCopy(),exp2.deepCopy(),exp3.deepCopy());
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        return typeEnv;
    }

    public String toString(){
        return variable + "=" + "(" + exp1.toString() + ")" +  "?" + exp2.toString() + ":" + exp3.toString();
    }
}
