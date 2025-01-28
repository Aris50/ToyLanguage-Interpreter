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
import model.values.IValue;

public class ForStmt implements IStmt{
    private final String variable;
    private final IExpression exp1;
    private final IExpression exp2;
    private final IExpression exp3;
    private final IStmt stmt;

    public ForStmt(String variable,IExpression exp1, IExpression exp2, IExpression exp3, IStmt stmt) {
        this.variable=variable;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.stmt=stmt;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        IValue val1=exp1.evaluate(state.getSymTable(), state.getHeap());
        IValue val2=exp2.evaluate(state.getSymTable(), state.getHeap());
        IValue val3=exp3.evaluate(state.getSymTable(), state.getHeap());
        if(val1.getType().equals(new IntType()) && val2.getType().equals(new IntType()) && val3.getType().equals(new IntType())){
            state.getExeStack().push(new CompStmt(new VarDecl(variable, new IntType()), new CompStmt
                    (new AssignStmt(variable, exp1),
                            new CompStmt(new WhileStmt
                                    (new RelationalExpression
                                            (new VariableExpression("v"), exp2, 1),stmt),
                                    new AssignStmt(variable, exp3)))));
        }else{
            throw new StatementException("Error. The expressions in the for instruction need to be of type int");
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new ForStmt(variable,exp1.deepCopy(),exp2.deepCopy(),exp3.deepCopy(),stmt.deepCopy());
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        IType type1 = exp1.typecheck(typeEnv);
        IType type2 = exp2.typecheck(typeEnv);
        IType type3 = exp3.typecheck(typeEnv);
        if(type1.equals(new IntType()) && type2.equals(new IntType()) && type3.equals(new IntType())){
            stmt.typecheck(typeEnv);
            return typeEnv;
    }
        else{
            throw new StatementException("Error. The expressions in the for instruction need to be of type int");
        }
    }

    public String toString(){
        return "for("+variable+"="+exp1.toString()+";"+variable+"<"+exp2.toString()+";"+variable+"="+exp3.toString()+")"+stmt.toString();
    }
}
