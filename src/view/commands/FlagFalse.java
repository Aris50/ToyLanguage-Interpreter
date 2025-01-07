package view.commands;
import Controller.Controller;

public class FlagFalse extends Command{
    private Controller ctr;
    public FlagFalse(Controller controller){
        super("7", "FlagFalse");
        ctr=controller;
    }
    public void execute(){

    }
}
