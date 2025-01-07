package model.examples;

import model.expressions.*;
import model.statements.*;
import model.types.*;
import model.values.*;

public class Example10 implements Example {
    public IStmt getExample() {
        IStmt stmt1 = new VarDecl("v", new RefType(new IntType()));
        IStmt stmt2 = new NewStmt("v", new ValueExpression(new IntValue(20)));
        IStmt stmt3 = new VarDecl("a", new RefType(new RefType(new IntType())));
        IStmt stmt4 = new NewStmt("a", new VariableExpression("v"));
        IStmt stmt5 = new NewStmt("v", new ValueExpression(new IntValue(30)));
        IStmt stmt6 = new PrintStmt(new HeapReadExpression(new HeapReadExpression(new VariableExpression("a"))));

        return new CompStmt(stmt1, new CompStmt(stmt2, new CompStmt(stmt3, new CompStmt(stmt4, new CompStmt(stmt5, stmt6)))));
    }
}