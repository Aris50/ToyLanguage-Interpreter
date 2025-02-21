package model.examples;

import model.expressions.ArithmeticExpression;
import model.expressions.HeapReadExpression;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.types.IntType;
import model.types.RefType;
import model.values.IntValue;

public class Example21 implements Example {

    @Override
    public IStmt getExample() {
        IStmt stmt11 = new PrintStmt(new HeapReadExpression(new VariableExpression("v3")));
        IStmt stmt10 = new CompStmt(new AwaitStmt("cnt"), stmt11);
        IStmt stmt9 = new CompStmt(new ForkStmt(new CompStmt(new AwaitStmt("cnt"), new CompStmt(new HeapWriteStmt("v2", new ArithmeticExpression(new HeapReadExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(10)), 3)), new CompStmt(new HeapWriteStmt("v2", new ArithmeticExpression(new HeapReadExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(10)), 3)), new PrintStmt(new HeapReadExpression(new VariableExpression("v2"))))))), stmt10);
        IStmt stmt8 = new CompStmt(new ForkStmt(new CompStmt(new AwaitStmt("cnt"), new CompStmt(new HeapWriteStmt("v1", new ArithmeticExpression(new HeapReadExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)), 3)), new PrintStmt(new HeapReadExpression(new VariableExpression("v1")))))), stmt9);
        IStmt stmt7 = new CompStmt(new NewBarrierStmt("cnt", new HeapReadExpression(new VariableExpression("v2"))), stmt8);
        IStmt stmt6 = new CompStmt(new NewStmt("v3", new ValueExpression(new IntValue(4))), stmt7);
        IStmt stmt5 = new CompStmt(new NewStmt("v2", new ValueExpression(new IntValue(3))), stmt6);
        IStmt stmt4 = new CompStmt(new NewStmt("v1", new ValueExpression(new IntValue(2))), stmt5);
        IStmt stmt3 = new CompStmt(new VarDecl("cnt", new IntType()), stmt4);
        IStmt stmt2 = new CompStmt(new VarDecl("v3", new RefType(new IntType())), stmt3);
        IStmt stmt1 = new CompStmt(new VarDecl("v2", new RefType(new IntType())), stmt2);
        IStmt stmt0 = new CompStmt(new VarDecl("v1", new RefType(new IntType())), stmt1);
        return stmt0;
    }
}
