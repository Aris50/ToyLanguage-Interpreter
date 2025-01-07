package model.examples;

import model.statements.*;
import model.expressions.*;
import model.types.*;
import model.values.*;

public class Example5 implements Example{
    public IStmt getExample() {
        return new CompStmt(
                new VarDecl("varf", new StringType()),
                new CompStmt(
                        new AssignStmt("varf", new ValueExpression(new StringValue("/Users/arisoniga/Desktop/Work/IE2/MAP/ASSIGNMENTS/A3/src/test.in"))),
                        new CompStmt(
                                new OpenRFile(new VariableExpression("varf")),
                                new CompStmt(
                                        new VarDecl("varc", new IntType()),
                                        new CompStmt(
                                                new ReadFile(new VariableExpression("varf"), "varc"),
                                                new CompStmt(
                                                        new PrintStmt(new VariableExpression("varc")),
                                                        new CompStmt(
                                                                new ReadFile(new VariableExpression("varf"), "varc"),
                                                                new CompStmt(
                                                                        new PrintStmt(new VariableExpression("varc")),
                                                                        new CloseRFile(new VariableExpression("varf"))
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