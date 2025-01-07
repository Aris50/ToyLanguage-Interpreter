package model.examples;

import model.expressions.*;
import model.statements.*;
import model.types.IntType;
import model.values.IntValue;

public class Example11 implements Example{
    public IStmt getExample() {
        return new CompStmt(
                new VarDecl("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExpression(new IntValue(4))),
                        new CompStmt(
                                new WhileStmt(
                                        new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), 5),
                                        new CompStmt(
                                                new PrintStmt(new VariableExpression("v")),
                                                new AssignStmt("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)),2))
                                        )
                                ),
                                new PrintStmt(new VariableExpression("v"))
                        )
                )
        );
    }
}