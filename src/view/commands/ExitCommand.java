package view.commands;
import view.commands.*;

public class ExitCommand extends Command{
    public ExitCommand(){
        super("0", "Exit");
    }
    public void execute(){
        System.exit(0);
    }
}
