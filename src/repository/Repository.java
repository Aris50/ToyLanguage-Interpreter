package repository;
import exceptions.RepoException;
import javafx.scene.control.Label;
import model.state.ProgramState;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.ListView;
import java.io.FileOutputStream;
import java.io.PrintStream;
import model.streams.DualOutputStream;



public class Repository implements IRepository{
    private List<ProgramState> states;
    private int currentStatePosition;
    private final String filename;

    private final ListView<String> outputListView;
    private final ListView<String> stackListView;
    private final ListView<String> heapListView;
    private final ListView<String> symTableListView;
    private final ListView<String> fileTableListView;
    private final ListView<String> prgStateIDListView;
    private final Label prgStateIdLabel;
    private final PrintStream logStream;


    public Repository (String file, ProgramState prg, ListView<String> outputListView, ListView<String> stackListView, ListView<String> heapListView, ListView<String> symTableListView, ListView<String> fileTableListView, ListView<String> prgStateIDListView, Label prgStateIdLabel) throws Exception{
        this.prgStateIdLabel = prgStateIdLabel;
        states = new ArrayList<ProgramState>();
        states.add(prg);
        currentStatePosition = 0;
        filename = file;
        this.outputListView = outputListView;
        this.stackListView = stackListView;
        this.heapListView = heapListView;
        this.symTableListView = symTableListView;
        this.fileTableListView = fileTableListView;
        this.prgStateIDListView = prgStateIDListView;
        this.logStream = new PrintStream(new DualOutputStream(new PrintStream(new FileOutputStream(filename,true)), outputListView, stackListView, heapListView, symTableListView, fileTableListView, prgStateIDListView, prgStateIdLabel));
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

    public void logPrgStateExec(ProgramState p, String stackText, String symTableText, String fileTableText, String heapTest, String prgStateIDText, String outText) throws RepoException {
        String statesSizeString = String.valueOf(states.size());
        logStream.println(p.toString()+"@"+stackText+"@"+symTableText+"@"+fileTableText+"@"+heapTest+"@"+prgStateIDText + "@" + "Total states:" + statesSizeString + "@" + outText);
        if(p.getExeStack().isEmpty()){
            outputListView.getItems().clear();
            stackListView.getItems().clear();
            heapListView.getItems().clear();
            symTableListView.getItems().clear();
            fileTableListView.getItems().clear();
            prgStateIDListView.getItems().clear();
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
