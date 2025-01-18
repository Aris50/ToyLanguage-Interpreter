package Controller;
import model.state.ProgramState;
import exceptions.ControllerException;
import repository.IRepository;
import java.util.List;

public interface IController {
    public ProgramState oneStep(ProgramState state) throws ControllerException;
    public void allStep();
    public void setFlag(boolean value);
    public IRepository getRepo();
    List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList);
    public List<ProgramState> getPrgStateList();
}
