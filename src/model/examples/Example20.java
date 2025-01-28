package model.examples;

import model.expressions.ArithmeticExpression;
import model.expressions.RelationalExpression;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.CompStmt;
import model.statements.IStmt;
import model.statements.*;
import model.types.*;
import model.values.*;

public class Example20 implements Example {
    @Override
    public IStmt getExample() {
        return new CompStmt(new VarDecl("v", new IntType()),
                new CompStmt(new VarDecl("x", new IntType()),
                        new CompStmt(new VarDecl("y", new IntType()),
                                new CompStmt(new AssignStmt("v", new ValueExpression(new IntValue(0))),
                                        new CompStmt(new RepeatStmt(
                                                new CompStmt(
                                                        new ForkStmt(new CompStmt(
                                                                new PrintStmt(new VariableExpression("v")),
                                                                new AssignStmt("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 2))
                                                        )),
                                                        new AssignStmt("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 1))
                                                ),
                                                new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(3)), 3)
                                        ),
                                                new CompStmt(new AssignStmt("x", new ValueExpression(new IntValue(1))),
                                                        new CompStmt(new NopStmt(),
                                                                new CompStmt(new AssignStmt("y", new ValueExpression(new IntValue(3))),
                                                                        new CompStmt(new NopStmt(),
                                                                                new PrintStmt(new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(10)), 3))
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }
}