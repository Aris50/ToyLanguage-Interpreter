package repository;
import exceptions.RepoException;
import model.state.ProgramState;
import java.util.List;

public interface IRepository {
    void add(ProgramState p);
    ProgramState getCurrent();
    void logPrgStateExec(ProgramState p) throws RepoException;

    List<ProgramState> getPrgList();
    void setPrgList(List<ProgramState> newList);

    void clear();
}
