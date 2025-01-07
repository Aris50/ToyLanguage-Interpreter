package model.examples;

import model.statements.*;
import model.expressions.*;
import model.types.*;
import model.values.*;

public class Example4 implements Example{
    public IStmt getExample() {
        return new CompStmt(
                new VarDecl("a", new IntType()),
                new CompStmt(
                        new AssignStmt("a", new ArithmeticExpression(new ValueExpression(new IntValue(10)), new ValueExpression(new IntValue(0)), 4)),
                        new PrintStmt(new VariableExpression("a"))
                )
        );
    }
}


