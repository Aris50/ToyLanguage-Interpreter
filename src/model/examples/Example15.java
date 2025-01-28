package model.examples;

import model.expressions.MulExpression;
import model.expressions.RelationalExpression;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.types.IntType;
import model.values.IntValue;

public class Example15 implements Example {
    public IStmt getExample() {
        return new CompStmt(new VarDecl("v1", new IntType()),
                new CompStmt(new VarDecl("v2", new IntType()),
                        new CompStmt(new AssignStmt("v1", new ValueExpression(new IntValue(2))),
                                new CompStmt(
                                        new AssignStmt("v2", new ValueExpression(new IntValue(3))),
                                        new IfStmt(new RelationalExpression(new VariableExpression("v2")),
                                                new PrintStmt(new MulExpression(new VariableExpression("v1"), new VariableExpression("v2"))),
                                                new PrintStmt(new VariableExpression("v1")))
                                ))));
    }
}