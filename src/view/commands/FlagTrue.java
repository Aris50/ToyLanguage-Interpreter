package view.commands;
import Controller.Controller;

public class FlagTrue extends Command{
    private Controller ctr;
    public FlagTrue(Controller controller){
        super("6", "FlagTrue");
        ctr = controller;
    }
    public void execute(){
        ctr.setFlag(true);
    }
}
