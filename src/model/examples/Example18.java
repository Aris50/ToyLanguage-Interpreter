package model.examples;

import model.expressions.*;
import model.statements.*;
import model.types.*;
import model.values.*;

public class Example18 implements Example {
    @Override
    public IStmt getExample() {
        IStmt stmt1 = new VarDecl("a", new RefType(new IntType()));
        IStmt stmt2 = new VarDecl("b", new RefType(new IntType()));
        IStmt stmt3 = new VarDecl("v", new IntType());
        IStmt stmt4 = new NewStmt("a", new ValueExpression(new IntValue(0)));
        IStmt stmt5 = new NewStmt("b", new ValueExpression(new IntValue(0)));
        IStmt stmt6 = new HeapWriteStmt("a", new ValueExpression(new IntValue(1)));
        IStmt stmt7 = new HeapWriteStmt("b", new ValueExpression(new IntValue(2)));
        IStmt stmt8 = new CondAssignStmt("v",
                new RelationalExpression(new HeapReadExpression(new VariableExpression("a")), new HeapReadExpression(new VariableExpression("b")), 1),
                new ValueExpression(new IntValue(100)),
                new ValueExpression(new IntValue(200))
        );
        IStmt stmt9 = new PrintStmt(new VariableExpression("v"));
        IStmt stmt10 = new CondAssignStmt("v",
                new RelationalExpression(new ArithmeticExpression(new HeapReadExpression(new VariableExpression("b")), new ValueExpression(new IntValue(2)), 2), new HeapReadExpression(new VariableExpression("a")), 5),
                new ValueExpression(new IntValue(100)),
                new ValueExpression(new IntValue(200))
        );
        IStmt stmt11 = new PrintStmt(new VariableExpression("v"));

        return new CompStmt(stmt1, new CompStmt(stmt2, new CompStmt(stmt3, new CompStmt(stmt4, new CompStmt(stmt5, new CompStmt(stmt6, new CompStmt(stmt7, new CompStmt(stmt8, new CompStmt(stmt9, new CompStmt(stmt10, stmt11))))))))));
    }
}