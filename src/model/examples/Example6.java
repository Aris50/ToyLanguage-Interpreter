package model.examples;

import model.statements.*;
import model.types.IntType;
import model.values.IntValue;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.expressions.RelationalExpression;

public class Example6 implements Example{
    public  IStmt getExample() {
        return new CompStmt(
                new VarDecl("x", new IntType()),
                new CompStmt(
                        new AssignStmt("x", new ValueExpression(new IntValue(20))),
                        new CompStmt(
                                new VarDecl("y", new IntType()),
                                new CompStmt(
                                        new AssignStmt("y", new ValueExpression(new IntValue(15))),
                                        new IfStmt(
                                                new RelationalExpression(new VariableExpression("x"), new VariableExpression("y"), 5),
                                                new PrintStmt(new VariableExpression("x")),
                                                new PrintStmt(new VariableExpression("y"))
                                        )
                                )
                        )
                )
        );
    }
}