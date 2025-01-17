package view.commands;
import Controller.Controller;
import model.state.ProgramState;
import view.commands.Command;

import java.util.List;


public class RunExample extends Command{
    private Controller ctr;
    public RunExample(String key, String desc, Controller ctr){
        super(key, desc);
        this.ctr = ctr;
    }
    public void execute(){
        try{
            ctr.allStep();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void executeOnce() {
        try {
            List<ProgramState> prgList = ctr.getRepo().getPrgList();
            ctr.oneStepForAllPrg(prgList);
            prgList = ctr.removeCompletedPrg(ctr.getRepo().getPrgList());
            ctr.getRepo().setPrgList(prgList);
        } catch (Exception e) {
            System.out.println("Error during one step execution: " + e.getMessage());
        }
    }

    public Controller getController() {
        return ctr;
    }
}
