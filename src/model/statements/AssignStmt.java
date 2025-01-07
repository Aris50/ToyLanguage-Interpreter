package model.statements;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.types.IType;
import model.values.IValue;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.adt.IMyMap;
import model.adt.IMyHeap;




public class AssignStmt implements IStmt {
    private String variable;
    private IExpression exp;

    public AssignStmt(String variable, IExpression exp) {
        this.variable = variable;
        this.exp = exp;
    }

    public String toString() {
        return variable + " = " + exp.toString();
    }

    public ProgramState execute(ProgramState state) throws ExpressionException, StatementException {
        if (!state.getSymTable().contains(this.variable)) {
            throw new ExpressionException("The variable is not defined.");
        }

        IValue evalValue = this.exp.evaluate(state.getSymTable(), state.getHeap());
        IType type = state.getSymTable().getElement(this.variable).getType();

        if (evalValue.getType().equals(type)) {
            state.getSymTable().insert(this.variable, evalValue);
        } else {
            throw new StatementException("The values do not match.");
        }

        //return state;
        return null;
    }

    public IStmt deepCopy() {
        return new AssignStmt(variable, exp.deepCopy());
    }

    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        IType typeVar = typeEnv.getElement(this.variable);
        IType typeExp = this.exp.typecheck(typeEnv);
        if (typeVar.equals(typeExp)) {
            return typeEnv;
        } else {
            throw new StatementException("Assignment: right hand side and left hand side have different types.");
        }
    }

}
