package model.examples;

import model.expressions.*;
import model.statements.*;
import model.types.*;
import model.values.*;

public class Example9 implements Example{
    public IStmt getExample() {
        IStmt stmt1 = new VarDecl("v", new RefType(new IntType()));
        IStmt stmt2 = new NewStmt("v", new ValueExpression(new IntValue(20)));
        IStmt stmt3 = new PrintStmt(new HeapReadExpression(new VariableExpression("v")));
        IStmt stmt4 = new HeapWriteStmt("v", new ValueExpression(new IntValue(30)));
        IStmt stmt5 = new PrintStmt(new ArithmeticExpression(
                new HeapReadExpression(new VariableExpression("v")),
                new ValueExpression(new IntValue(5)),
                1
        ));

        return new CompStmt(stmt1, new CompStmt(stmt2, new CompStmt(stmt3, new CompStmt(stmt4, stmt5))));
    }
}