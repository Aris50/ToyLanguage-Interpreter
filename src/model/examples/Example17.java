package model.examples;

import model.expressions.RelationalExpression;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;

public class Example17 implements Example{
    @Override
    public IStmt getExample() {
        return new CompStmt(new VarDecl("b", new BoolType()),
                new CompStmt(new VarDecl("c", new IntType()),
                        new CompStmt(new AssignStmt("b", new ValueExpression(new BoolValue(true))),
                                new CompStmt(new CondAssignStmt(   "c", new VariableExpression("b"), new ValueExpression(new IntValue(100)),new ValueExpression(new IntValue(200))),
                                        new CompStmt(new PrintStmt(new VariableExpression("c")),
                                                new CompStmt(new CondAssignStmt("c",new ValueExpression(new BoolValue(false)), new ValueExpression(new IntValue(100)), new ValueExpression(new IntValue(200))),
                                                        new PrintStmt(new VariableExpression("c"))))))));


    }
}
