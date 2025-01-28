package model.examples;

import model.expressions.*;
import model.statements.*;
import model.types.*;
import model.values.*;

public class Example19 implements Example {
    @Override
    public IStmt getExample() {
        return new CompStmt(new VarDecl("v", new IntType()),new CompStmt(
                new VarDecl("a", new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("a", new ValueExpression(new IntValue(20))),
                        new CompStmt(
                                new ForStmt(
                                        "v",
                                        new ValueExpression(new IntValue(0)),
                                         new ValueExpression(new IntValue(3)),
                                        new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 1),
                                        new ForkStmt(
                                                new CompStmt(
                                                        new PrintStmt(new VariableExpression("v")),
                                                        new AssignStmt("v", new ArithmeticExpression(new VariableExpression("v"), new HeapReadExpression(new VariableExpression("a")), 2))
                                                )
                                        )
                                ),
                                new PrintStmt(new HeapReadExpression(new VariableExpression("a")))
                        )
                )
        ));
    }
}