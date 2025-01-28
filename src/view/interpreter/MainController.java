package view.interpreter;

import Controller.Controller;
import exceptions.ExpressionException;
import exceptions.StatementException;
import exceptions.TypecheckException;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.adt.*;
import model.examples.*;
import model.state.ProgramState;
import model.statements.IStmt;
import repository.IRepository;
import repository.Repository;
import view.commands.RunExample;
import view.interpreter.*;

import java.util.List;
import java.util.Map;

public class MainController {
    @FXML
    private ListView<String> exampleListView;
    @FXML
    private ListView<String> outputListView;
    @FXML
    private ListView<String> executingListView;
    @FXML
    private ListView<String> stackListView;
    @FXML
    private ListView<String> fileTableListView;
    @FXML
    private ListView<String> prgStateIDListView;
    @FXML
    private Label prgStateIdLabel;
    @FXML
    private Label executingLabel;
    ///TABLES
    @FXML
    private TableView<SymTableRow> symTableView;
    @FXML
    private TableColumn<SymTableRow, String> symTableVarNameColumn;
    @FXML
    private TableColumn<SymTableRow, String> symTableValueColumn;

    @FXML
    private TableView<HeapTableRow> heapTableView;
    @FXML
    private TableColumn<HeapTableRow, Number> heapTableAddressColumn; // or Integer
    @FXML
    private TableColumn<HeapTableRow, String> heapTableValueColumn;
    private RunExample currentRunExample;
    private String currentSelectedExample;
    private ProgramState selectedProgram;
    ///
    @FXML
    public void initialize() {
        symTableVarNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("varName")   // calls getVarName()
        );
        symTableValueColumn.setCellValueFactory(
                new PropertyValueFactory<>("value")     // calls getValue()
        );

        // Heap Table columns
        heapTableAddressColumn.setCellValueFactory(
                new PropertyValueFactory<>("address")   // calls getAddress()
        );
        heapTableValueColumn.setCellValueFactory(
                new PropertyValueFactory<>("value")     // calls getValue()
        );
        exampleListView.getItems().addAll(
                "Example 1", "Example 2", "Example 3", "Example 4", "Example 5",
                "Example 6", "Example 7", "Example 8", "Example 9", "Example 10",
                "Example 11", "Example 12", "Example 13", "Example 14", "Example 15", "Example 16", "Example 17", "Example 18", "Example 19", "Example 20"
        );

        exampleListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateExecutingListView(newValue);
                currentSelectedExample = newValue;
                executingLabel.setText("Currently Selected: " + newValue);
                clearAll();
            }
        });
        prgStateIDListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                List<ProgramState> prgList = currentRunExample.getController().getRepo().getPrgList();
                int id=Integer.parseInt(newValue);
                var selectedProgram=prgList.stream().filter(p->p.getId()==id).toList();
                if(!(selectedProgram.isEmpty())){
                    this.selectedProgram=selectedProgram.getFirst();
                    updateUi();
                }
            }
        });
    }




    private void updateExecutingListView(String selectedExample) {
        Example example;
        switch (selectedExample) {
            case "Example 1":
                example = new Example1();
                break;
            case "Example 2":
                example = new Example2();
                break;
            case "Example 3":
                example = new Example3();
                break;
            case "Example 4":
                example = new Example4();
                break;
            case "Example 5":
                example = new Example5();
                break;
            case "Example 6":
                example = new Example6();
                break;
            case "Example 7":
                example = new Example7();
                break;
            case "Example 8":
                example = new Example8();
                break;
            case "Example 9":
                example = new Example9();
                break;
            case "Example 10":
                example = new Example10();
                break;
            case "Example 11":
                example = new Example11();
                break;
            case "Example 12":
                example = new Example12();
                break;
            case "Example 13":
                example = new Example13();
                break;
            case "Example 14":
                example = new Example14();
                break;
            case "Example 15":
                example = new Example15();
                break;
            case "Example 16":
                example = new Example16();
                break;
            case "Example 17":
                example = new Example17();
                break;
            case "Example 18":
                example = new Example18();
                break;
            case "Example 19":
                example = new Example19();
                break;
            case "Example 20":
                example = new Example20();
                break;
            default:
                showAlert("Invalid example", "The selected example is not valid.");
                return;
        }

        String exampleString = example.getExample().toString();
        executingListView.getItems().clear();
        /*
        String[] statements = exampleString.split(";");
        for (String statement : statements) {
            executingListView.getItems().add(statement.trim()+';');
        }
        */
        executingListView.getItems().add(exampleString);
    }

    @FXML
    private void handleRunExample() {
        String selectedExample = exampleListView.getSelectionModel().getSelectedItem();
        if (selectedExample == null) {
            showAlert("No example selected", "Please select an example to run.");
            return;
        }

        Example example;
        String logFilePath;
        switch (selectedExample) {
            case "Example 1":
                example = new Example1();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log1.txt";
                break;
            case "Example 2":
                example = new Example2();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log2.txt";
                break;
            case "Example 3":
                example = new Example3();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log3.txt";
                break;
            case "Example 4":
                example = new Example4();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log4.txt";
                break;
            case "Example 5":
                example = new Example5();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log5.txt";
                break;
            case "Example 6":
                example = new Example6();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log6.txt";
                break;
            case "Example 7":
                example = new Example7();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log7.txt";
                break;
            case "Example 8":
                example = new Example8();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log8.txt";
                break;
            case "Example 9":
                example = new Example9();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log9.txt";
                break;
            case "Example 10":
                example = new Example10();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log10.txt";
                break;
            case "Example 11":
                example = new Example11();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log11.txt";
                break;
            case "Example 12":
                example = new Example12();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log12.txt";
                break;
            case "Example 13":
                example = new Example13();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log13.txt";
                break;
            case "Example 14":
                example = new Example14();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log14.txt";
                break;
            case "Example 15":
                example = new Example15();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log15.txt";
                break;
            case "Example 16":
                example = new Example16();
                logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log16.txt";
                break;
            case "Example 17":
                example = new Example17();
                logFilePath="/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log17.txt";
                break;
            case "Example 18":
                example = new Example18();
                logFilePath="/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log18.txt";
                break;
            case "Example 19":
                example = new Example19();
                logFilePath="/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log19.txt";
                break;
            case "Example 20":
                example=new Example20();
                logFilePath="/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log20.txt";
                break;
            default:
                showAlert("Invalid example", "The selected example is not valid.");
                return;
        }

        try {
            IStmt stmt = example.getExample();
            stmt.typecheck(new MyMap<>());
            ProgramState prg = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), stmt, new MyMap<>(), new MyHeap());
            try {
                IRepository repo = new Repository(logFilePath, prg);
                Controller ctr = new Controller(repo);
                RunExample runExample = new RunExample(selectedExample, stmt.toString(), ctr);
                currentRunExample=runExample;
                runExample.execute();
                showFinalOutput();
                currentRunExample=null;
            }
            catch (Exception e) {
                showAlert("Repository error", "Error creating repository: " + e.getMessage());
                return;
            }

        } catch (TypecheckException | StatementException | ExpressionException e) {
            showAlert("Typecheck error", "Typecheck error in " + selectedExample + ": " + e.getMessage());
        }
    }

    private void updateUi() {
        var prgList = currentRunExample.getController().getPrgStateList();
        if (selectedProgram == null || !(prgList.isEmpty()) && prgList.stream().filter(p -> p.getId() == selectedProgram.getId()).toList().isEmpty()) {
            selectedProgram = prgList.getFirst();
        }
        outputListView.getItems().clear();
        outputListView.getItems().addAll(selectedProgram.getOut().stream().map(Object::toString).toList());


        // 1) Clear current items
        symTableView.getItems().clear();
        heapTableView.getItems().clear();

        // 2) Populate Symbol Table
        symTableView.getItems().addAll(
                selectedProgram.getSymTable().getContent().entrySet().stream()
                        .map(entry -> new SymTableRow(entry.getKey(), entry.getValue().toString()))
                        .toList()
        );
        // 3) Populate Heap Table
        heapTableView.getItems().addAll(
                selectedProgram.getHeap().getContent().entrySet().stream()
                        .map(entry -> new HeapTableRow(entry.getKey(), entry.getValue().toString()))
                        .toList()
        );

        fileTableListView.getItems().clear();
        fileTableListView.getItems().addAll(selectedProgram.getFileTable().stream().map(Map.Entry::getKey).toList());

        prgStateIdLabel.setText("Total Id's:" + prgList.size());
        stackListView.getItems().clear();
        List<String> stackItems = selectedProgram.getExeStack().stream().map(Object::toString).toList();
        for (int i = stackItems.size() - 1; i >= 0; i--) {
            stackListView.getItems().add(stackItems.get(i));
        }
    }
    @FXML
    private void handleOneStepButton() {
        if (currentRunExample == null && exampleListView.getSelectionModel().getSelectedItem() == null) {
            showAlert("No example selected", "Please select an example to run.");
            return;
        } else if (currentRunExample == null && exampleListView.getSelectionModel().getSelectedItem().equals(currentSelectedExample)) {
            String selectedExample = exampleListView.getSelectionModel().getSelectedItem();
            if (selectedExample == null) {
                showAlert("No example selected", "Please select an example to run.");
                return;
            }

            Example example;
            String logFilePath;
            switch (selectedExample) {
                case "Example 1":
                    example = new Example1();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log1.txt";
                    break;
                case "Example 2":
                    example = new Example2();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log2.txt";
                    break;
                case "Example 3":
                    example = new Example3();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log3.txt";
                    break;
                case "Example 4":
                    example = new Example4();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log4.txt";
                    break;
                case "Example 5":
                    example = new Example5();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log5.txt";
                    break;
                case "Example 6":
                    example = new Example6();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log6.txt";
                    break;
                case "Example 7":
                    example = new Example7();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log7.txt";
                    break;
                case "Example 8":
                    example = new Example8();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log8.txt";
                    break;
                case "Example 9":
                    example = new Example9();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log9.txt";
                    break;
                case "Example 10":
                    example = new Example10();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log10.txt";
                    break;
                case "Example 11":
                    example = new Example11();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log11.txt";
                    break;
                case "Example 12":
                    example = new Example12();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log12.txt";
                    break;
                case "Example 13":
                    example = new Example13();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log13.txt";
                    break;
                case "Example 14":
                    example = new Example14();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log14.txt";
                    break;
                case "Example 15":
                    example = new Example15();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log15.txt";
                    break;
                case "Example 16":
                    example = new Example16();
                    logFilePath = "/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log16.txt";
                    break;
                case "Example 17":
                    example = new Example17();
                    logFilePath="/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log17.txt";
                    break;
                case "Example 18":
                    example = new Example18();
                    logFilePath="/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log18.txt";
                    break;
                case "Example 19":
                    example = new Example19();
                    logFilePath="/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log19.txt";
                    break;
                case "Example 20":
                    example = new Example20();
                    logFilePath="/Users/arisoniga/Desktop/Work/UBB_FMI_INFO/IE2/MAP/ASSIGNMENTS/A6/src/log20.txt";
                    break;
                default:
                    showAlert("Invalid example", "The selected example is not valid.");
                    return;
            }

            try {
                clearAll();
                IStmt stmt = example.getExample();
                stmt.typecheck(new MyMap<>());
                ProgramState prg = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), stmt, new MyMap<>(), new MyHeap());
                try {
                    IRepository repo = new Repository(logFilePath, prg);
                    Controller ctr = new Controller(repo);
                    RunExample runExample = new RunExample(selectedExample, stmt.toString(), ctr);
                    currentRunExample = runExample;
                    currentRunExample.executeOnce();
                    prgStateIDListView.getItems().clear();
                    prgStateIDListView.getItems().addAll(ctr.getPrgStateList().stream().map(p->Integer.toString(p.getId())).toList());
                    updateUi();
                } catch (Exception e) {
                    showAlert("Repository error", "Error creating repository: " + e.getMessage());
                    return;
                }

            } catch (TypecheckException | StatementException | ExpressionException e) {
                showAlert("Typecheck error", "Typecheck error in " + selectedExample + ": " + e.getMessage());
            }
        } else {
            try {
                currentRunExample.executeOnce();
                updateUi();
                prgStateIDListView.getItems().clear();
                prgStateIDListView.getItems().addAll(currentRunExample.getController().getPrgStateList().stream().map(p->Integer.toString(p.getId())).toList());
                if (currentRunExample.getController().getRepo().getPrgList().isEmpty()) {
                    showDone();
                    currentRunExample = null;
                }
            }
            catch (Exception e) {
                showAlert("Execution error", "Error during execution: " + e.getMessage());
                currentRunExample=null;
            }

        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void handleClearAll() {
        clearAll();
    }

    private void clearAll() {
        outputListView.getItems().clear();
        stackListView.getItems().clear();
        heapTableView.getItems().clear();
        symTableView.getItems().clear();
        fileTableListView.getItems().clear();
        prgStateIDListView.getItems().clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showDone(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("CONGRATULATIONS");
        alert.setHeaderText(null);
        alert.setContentText("Program: " + currentSelectedExample + " has finished executing.");
        alert.showAndWait();
    }

    private void showFinalOutput(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Final Output");
        alert.setHeaderText(null);
        alert.setContentText("Program" + currentSelectedExample + " has successfully finished executing. The complete execution can be found in the log file");
        alert.showAndWait();
    }
}