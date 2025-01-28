package model.examples;

import model.expressions.ArithmeticExpression;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.types.IntType;
import model.values.IntValue;

public class Example16 implements Example {
    @Override
    public IStmt getExample() {
        return new CompStmt(
                new VarDecl("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExpression(new IntValue(20))),
                        new CompStmt(
                                new WaitStmt(new ValueExpression(new IntValue(10))),
                                new PrintStmt(new ArithmeticExpression(
                                        new VariableExpression("v"),
                                        new ValueExpression(new IntValue(10)),
                                        3
                                ))
                        )
                )
        );
    }
}