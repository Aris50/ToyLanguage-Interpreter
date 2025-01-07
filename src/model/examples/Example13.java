package model.examples;

import model.statements.*;
import model.types.*;
import model.values.*;
import model.expressions.*;

public class Example13 implements Example{
    public IStmt getExample() {
        return new CompStmt(
                new VarDecl("a", new IntType()),
                new CompStmt(
                        new AssignStmt("a", new ValueExpression(new IntValue(10))),
                        new CompStmt(
                                new VarDecl("b", new BoolType()),
                                new CompStmt(
                                        new AssignStmt("b", new ValueExpression(new BoolValue(true))),
                                        new AssignStmt("a", new VariableExpression("b"))
                                )
                        )
                )
        );
    }
}