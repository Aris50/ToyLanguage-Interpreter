// src/model/examples/example2.java
package model.examples;

import model.statements.*;
import model.expressions.*;
import model.types.*;
import model.values.*;


//1-addition
//3-multiplication
public class Example2 implements Example{
    public IStmt getExample() {
        return new CompStmt(
                new VarDecl("a", new IntType()),
                new CompStmt(
                        new VarDecl("b", new IntType()),
                        new CompStmt(
                                new AssignStmt("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), 3), 1)),
                                new CompStmt(
                                        new AssignStmt("b", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), 1)),
                                        new PrintStmt(new VariableExpression("b"))
                                )
                        )
                )
        );
    }
}