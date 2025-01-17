package repository;
import exceptions.RepoException;
import model.state.ProgramState;
import java.util.List;

public interface IRepository {
    void add(ProgramState p);
    ProgramState getCurrent();
    void logPrgStateExec(ProgramState p, String stackText, String symTableText, String fileTableText, String heapTest, String prgStateIDText, String outText) throws RepoException;
    List<ProgramState> getPrgList();
    void setPrgList(List<ProgramState> newList);

    void clear();
}
