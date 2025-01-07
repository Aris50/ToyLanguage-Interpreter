package view.interpreter;
import Controller.Controller;
import exceptions.ExpressionException;
import exceptions.StatementException;
import exceptions.TypecheckException;
import javafx.fxml.FXML;
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
    private List<Example> examples;
    @FXML
    private ListView<String> exampleListView;
    @FXML
    private ListView<String> outputListView;

    @FXML
    public void initialize() {
        exampleListView.getItems().addAll(
                "Example 1", "Example 2", "Example 3", "Example 4", "Example 5",
                "Example 6", "Example 7", "Example 8", "Example 9", "Example 10",
                "Example 11", "Example 12", "Example 13", "Example 14"
        );
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
            IRepository repo = new Repository(logFilePath, prg);
            Controller ctr = new Controller(repo);
            RunExample runExample = new RunExample(selectedExample, stmt.toString(), ctr);
            runExample.execute();
            String result = ctr.getPrgStatesOutput();
            if(!(outputListView.getItems().isEmpty()))
                outputListView.getItems().removeLast();
            outputListView.getItems().add(result);

        } catch (TypecheckException | StatementException | ExpressionException e) {
            showAlert("Typecheck error", "Typecheck error in " + selectedExample + ": " + e.getMessage());
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    private void showAlert(String title, String message){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}