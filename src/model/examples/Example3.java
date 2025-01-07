// src/model/examples/example3.java
package model.examples;

import model.statements.*;
import model.expressions.*;
import model.types.*;
import model.values.*;

public class Example3 implements Example {
    public IStmt getExample() {
        return new CompStmt(
                new VarDecl("a", new BoolType()),
                new CompStmt(
                        new VarDecl("v", new IntType()),
                        new CompStmt(
                                new AssignStmt("a", new ValueExpression(new BoolValue(true))),
                                new CompStmt(
                                        new IfStmt(
                                                new VariableExpression("a"),
                                                new AssignStmt("v", new ValueExpression(new IntValue(2))),
                                                new AssignStmt("v", new ValueExpression(new IntValue(3)))
                                        ),
                                        new PrintStmt(new VariableExpression("v"))
                                )
                        )
                )
        );
    }
}