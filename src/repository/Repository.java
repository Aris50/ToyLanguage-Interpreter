package repository;
import exceptions.RepoException;
import javafx.scene.control.Label;
import model.state.ProgramState;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

import javafx.scene.control.ListView;


public class Repository implements IRepository{
    private List<ProgramState> states;
    private int currentStatePosition;
    private final String filename;


    public Repository (String file, ProgramState prg) throws Exception{
        states = new ArrayList<ProgramState>();
        states.add(prg);
        currentStatePosition = 0;
        filename = file;
        try{
            new PrintWriter(file).close();
        } catch (IOException e){
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
        try(FileWriter f = new FileWriter(this.filename,true)){
            f.write(p.toString());
        } catch (IOException e) {
            throw new RepoException(e.toString());
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
