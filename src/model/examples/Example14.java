package model.examples;

import model.statements.*;
import model.types.*;
import model.values.*;
import model.expressions.*;

public class Example14 implements Example{
    public IStmt getExample() {
        return new CompStmt(
                new VarDecl("a", new IntType()),
                new PrintStmt(new HeapReadExpression(new VariableExpression("a")))
        );
    }
}