package model.examples;

import model.statements.*;
import model.types.IntType;
import model.values.IntValue;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;

public class Example1 implements Example{
    public IStmt getExample() {
        return new CompStmt(
                new VarDecl("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExpression(new IntValue(2))),
                        new PrintStmt(new VariableExpression("v"))
                )
        );
    }
}