/*package view.interpreter;

import Controller.Controller;
import model.adt.*;
import model.state.ProgramState;
import model.statements.IStmt;
import repository.IRepository;
import repository.Repository;
import view.textMenu.TextMenu;
import view.commands.ExitCommand;
import view.commands.RunExample;
import model.examples.*;
import exceptions.TypecheckException;
import exceptions.StatementException;
import exceptions.ExpressionException;

public class Interpreter {

    public static void createCommand(TextMenu menu, Example example, String logFilePath, String exampleNumber) {
        try {
            IStmt stmt = example.getExample();
            stmt.typecheck(new MyMap<>());
            ProgramState prg = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), stmt, new MyMap<>(), new MyHeap());
            IRepository repo = new Repository(logFilePath, prg);
            Controller ctr = new Controller(repo);
            menu.addCommand(new RunExample(exampleNumber, stmt.toString(), ctr));
        } catch (TypecheckException | StatementException | ExpressionException e) {
            System.out.println("Typecheck error in Example" + exampleNumber + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand());

        createCommand(menu, new Example1(), "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log1.txt", "1");
        createCommand(menu, new Example2(), "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log2.txt", "2");
        createCommand(menu, new Example3(), "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log3.txt", "3");
        createCommand(menu, new Example4(), "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log4.txt", "4");
        createCommand(menu, new Example5(), "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log5.txt", "5");
        createCommand(menu, new Example6(), "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log6.txt", "6");
        createCommand(menu, new Example7(), "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log7.txt", "7");
        createCommand(menu, new Example8(), "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log8.txt", "8");
        createCommand(menu, new Example9(), "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log9.txt", "9");
        createCommand(menu, new Example10(), "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log10.txt", "10");
        createCommand(menu, new Example11(), "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log11.txt", "11");
        createCommand(menu, new Example12(), "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log12.txt", "12");
        createCommand(menu, new Example13(), "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log13.txt", "13");
        createCommand(menu, new Example14(), "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log14.txt", "14");
        menu.show();
    }
}
*/
