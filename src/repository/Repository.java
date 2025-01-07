package repository;
import exceptions.RepoException;
import model.state.ProgramState;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class Repository implements IRepository{
    private List<ProgramState> states;
    private int currentStatePosition;
    private String filename;
    public Repository (String file, ProgramState prg){
        states = new ArrayList<ProgramState>();
        states.add(prg);
        currentStatePosition = 0;
        filename = file;
    }

    public Repository(String file) {
        states = new ArrayList<ProgramState>();
        currentStatePosition = 0;
        filename = file;
        // Clear the contents of the files
        try {
            new PrintWriter(file).close();
        } catch (IOException e) {
            throw new RepoException("CANNOT CLEAR THE FILE CONTENTS");
        }

    }

    public void add(ProgramState p) {
        states.add(p);
        currentStatePosition++;
    }

    public void remove(ProgramState p) {
        states.remove(p);
    }
    //TODO-CHANGE!!
    public ProgramState getCurrent() {
        return states.get(currentStatePosition);
    }


    public void logPrgStateExec(ProgramState p) throws RepoException {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
            writer.println(p);
            writer.close();
        } catch (IOException io) {
            throw new RepoException("CANNOT WRITE IN THE FILE");
        }
    }

    public void clear() {
        states.clear();
    }

    public List<ProgramState> getPrgList(){
        return this.states;
    }

    public void setPrgList(List<ProgramState> newList){
        this.states = newList;
    }
}
