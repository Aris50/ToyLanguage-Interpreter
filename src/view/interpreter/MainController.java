package view.interpreter;

import Controller.Controller;
import exceptions.ExpressionException;
import exceptions.StatementException;
import exceptions.TypecheckException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.adt.*;
import model.examples.*;
import model.state.ProgramState;
import model.statements.IStmt;
import repository.IRepository;
import repository.Repository;
import view.commands.RunExample;

import java.util.List;

public class MainController {
    @FXML
    private ListView<String> exampleListView;
    @FXML
    private ListView<String> outputListView;
    @FXML
    private ListView<String> executingListView;
    @FXML
    private ListView<String> symTableListView;
    @FXML
    private ListView<String> stackListView;
    @FXML
    private ListView<String> fileTableListView;
    @FXML
    private ListView<String> heapListView;
    @FXML
    private ListView<String> prgStateIDListView;

    @FXML
    private Label prgStateIdLabel;
    private RunExample currentRunExample;
    private String currentSelectedExample;

    @FXML
    public void initialize() {
        exampleListView.getItems().addAll(
                "Example 1", "Example 2", "Example 3", "Example 4", "Example 5",
                "Example 6", "Example 7", "Example 8", "Example 9", "Example 10",
                "Example 11", "Example 12", "Example 13", "Example 14"
        );

        exampleListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateExecutingListView(newValue);
                currentSelectedExample = newValue;
            }
        });
        prgStateIDListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                List<ProgramState> prgList = currentRunExample.getController().getRepo().getPrgList();
                for (ProgramState prg : prgList) {
                    if (prg.getId() == Integer.parseInt(newValue)) {

                        stackListView.getItems().clear();
                        symTableListView.getItems().clear();

                        stackListView.getItems().addAll(prg.getExeStack().toString());
                        symTableListView.getItems().addAll(prg.getSymTable().toString());


                    }
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
            default:
                showAlert("Invalid example", "The selected example is not valid.");
                return;
        }

        String exampleString = example.getExample().toString();
        String[] statements = exampleString.split(";");
        executingListView.getItems().clear();
        for (String statement : statements) {
            executingListView.getItems().add(statement.trim()+';');
        }
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
            default:
                showAlert("Invalid example", "The selected example is not valid.");
                return;
        }

        try {
            IStmt stmt = example.getExample();
            stmt.typecheck(new MyMap<>());
            ProgramState prg = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), stmt, new MyMap<>(), new MyHeap());
            try {
                IRepository repo = new Repository(logFilePath, prg, outputListView, stackListView, heapListView, symTableListView, fileTableListView, prgStateIDListView, prgStateIdLabel);
                Controller ctr = new Controller(repo);
                RunExample runExample = new RunExample(selectedExample, stmt.toString(), ctr);
                runExample.execute();
            }
            catch (Exception e) {
                showAlert("Repository error", "Error creating repository: " + e.getMessage());
                return;
            }

        } catch (TypecheckException | StatementException | ExpressionException e) {
            showAlert("Typecheck error", "Typecheck error in " + selectedExample + ": " + e.getMessage());
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
                    IRepository repo = new Repository(logFilePath, prg, outputListView, stackListView, heapListView, symTableListView, fileTableListView, prgStateIDListView, prgStateIdLabel);
                    Controller ctr = new Controller(repo);
                    RunExample runExample = new RunExample(selectedExample, stmt.toString(), ctr);
                    currentRunExample = runExample;
                    currentRunExample.executeOnce();
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
                if (currentRunExample.getController().getRepo().getPrgList().isEmpty()) {
                    showDone();
                    currentRunExample = null;
                }
            } catch (Exception e) {
                showAlert("Execution error", "Error during execution: " + e.getMessage());
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
        heapListView.getItems().clear();
        symTableListView.getItems().clear();
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
}