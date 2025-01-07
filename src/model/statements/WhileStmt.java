package model.statements;

import model.adt.IMyStack;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.values.BoolValue;
import model.values.IValue;
import model.types.*;
import model.adt.IMyMap;

public class WhileStmt implements IStmt {
    private IExpression expression;
    private IStmt statement;

    public WhileStmt(IExpression expression, IStmt statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException{
        IValue condition = expression.evaluate(state.getSymTable(), state.getHeap());
        if (condition instanceof BoolValue) {
            BoolValue boolCondition = (BoolValue) condition;
            if (boolCondition.getValue()) {
                IMyStack<IStmt> stack = state.getExeStack();
                stack.push(this);
                stack.push(statement);
            }
        } else {
            throw new StatementException("Condition expression is not a boolean");
        }
        return null;
    }


    public IStmt deepCopy() {
        return new WhileStmt(expression.deepCopy(), statement.deepCopy());
    }

    @Override
    public String toString() {
        return "while(" + expression.toString() + ") " + statement.toString();
    }

    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws StatementException {
        IType typeExp = expression.typecheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            statement.typecheck(typeEnv);
            return typeEnv;
        } else {
            throw new StatementException("The condition of WHILE has not the type bool.");
        }
    }

}