/*package view;

import Controller.IController;
import model.state.ProgramState;
import exceptions.ControllerException;
import java.util.Scanner;
import model.examples.Example1;
import model.examples.Example2;
import model.examples.Example3;
import model.examples.Example4;
import model.examples.Example5;
import model.statements.IStmt;
import model.adt.*;
import model.values.IValue;
import java.io.BufferedReader;

import model.examples.Example6;

public class View implements IView {
    private IController controller;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public View(IController controller) {
        this.controller = controller;
    }

    @Override
    public void inputProgram() {
        System.out.println("Input program is predefined for this example.");
    }

    @Override
    public void displayMenu() {
        System.out.println(ANSI_BLUE +"1. Execute Program 1");
        System.out.println("2. Execute Program 2");
        System.out.println("3. Execute Program 3");
        System.out.println("4. Execute program 4");
        System.out.println("5. Execute Program 5");
        System.out.println("6. Set flag to true (show all steps)");
        System.out.println("7. Set flag to false (show only final print)");
        System.out.println("0. Exit" + ANSI_RESET);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        boolean ok = true;

        while (running) {
            displayMenu();
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    ///Preparing to create program state
                    controller.getRepo().clear();
                    IMyStack<IStmt> exeStack = new MyStack<>();
                    IMyMap<String, IValue> symTable = new MyMap<>();
                    IMyList<IValue> out = new MyList<>();
                    IMyMap<String, BufferedReader> fileTable = new MyMap<>();


                    IStmt exampleProgram = Example1.getExample();
                    ProgramState initialState = new ProgramState(exeStack, symTable, out, exampleProgram, fileTable, );
                    controller.getRepo().add(initialState);
                    if(ok==true){
                        System.out.println("-------------------------------------\n");
                        ok=false;
                    }
                    executeProgram();
                    break;
                case 2:
                    ///Preparing to create program state
                    controller.getRepo().clear();
                    IMyStack<IStmt> exeStack1 = new MyStack<>();
                    IMyMap<String, IValue> symTable1 = new MyMap<>();
                    IMyList<IValue> out1 = new MyList<>();
                    IMyMap<String, BufferedReader> fileTable1 = new MyMap<>();
                    IMyHeap heap1 = new MyHeap();

                    IStmt exampleProgram1 = Example2.getExample();
                    ProgramState initialState1 = new ProgramState(exeStack1, symTable1, out1, exampleProgram1, fileTable1,heap1);
                    controller.getRepo().add(initialState1);
                    if(ok==true){
                        System.out.println("-------------------------------------\n");
                        ok=false;
                    }
                    executeProgram();
                    break;
                case 3:
                    ///Preparing to create program state
                    controller.getRepo().clear();
                    IMyStack<IStmt> exeStack2 = new MyStack<>();
                    IMyMap<String, IValue> symTable2 = new MyMap<>();
                    IMyList<IValue> out2 = new MyList<>();
                    IMyMap<String, BufferedReader> fileTable2 = new MyMap<>();

                    IStmt exampleProgram2 = Example3.getExample();
                    ProgramState initialState2 = new ProgramState(exeStack2, symTable2, out2, exampleProgram2, fileTable2);
                    controller.getRepo().add(initialState2);
                    if(ok==true){
                        System.out.println("-------------------------------------\n");
                        ok=false;
                    }
                    executeProgram();
                    break;
                case 6:
                    System.out.println(ANSI_GREEN + "Flag set to true. All steps will be shown." + ANSI_RESET + "\n");
                    controller.setFlag(true);
                    break;
                case 7:
                    System.out.println(ANSI_RED + "Flag set to false. Only final print will be shown." + ANSI_RESET + "\n");
                    controller.setFlag(false);
                    break;
                case 4:
                    controller.getRepo().clear();
                    IMyStack<IStmt> exeStack3 = new MyStack<>();
                    IMyMap<String, IValue> symTable3 = new MyMap<>();
                    IMyList<IValue> out3 = new MyList<>();
                    IMyMap<String, BufferedReader> fileTable3 = new MyMap<>();

                    IStmt exampleProgram3 = Example4.getExample();
                    ProgramState initialState3 = new ProgramState(exeStack3, symTable3, out3, exampleProgram3, fileTable3);
                    controller.getRepo().add(initialState3);
                    if(ok==true){
                        System.out.println("-------------------------------------\n");
                        ok=false;
                    }
                    executeProgram();
                    break;
                case 5:
                    controller.getRepo().clear();
                    IMyStack<IStmt> exeStack4 = new MyStack<>();
                    IMyMap<String, IValue> symTable4 = new MyMap<>();
                    IMyList<IValue> out4 = new MyList<>();
                    IMyMap<String, BufferedReader> fileTable4 = new MyMap<>();

                    IStmt exampleProgram4 = Example5.getExample();
                    ProgramState initialState4 = new ProgramState(exeStack4, symTable4, out4, exampleProgram4, fileTable4);
                    controller.getRepo().add(initialState4);
                    if(ok==true){
                        System.out.println("-------------------------------------\n");
                        ok=false;
                    }
                    executeProgram();
                    break;
                case 8:
                    controller.getRepo().clear();
                    IMyStack<IStmt> exeStack5 = new MyStack<>();
                    IMyMap<String, IValue> symTable5 = new MyMap<>();
                    IMyList<IValue> out5 = new MyList<>();
                    IMyMap<String, BufferedReader> fileTable5 = new MyMap<>();

                    IStmt exampleProgram5 = Example6.getExample();
                    ProgramState initialState5 = new ProgramState(exeStack5, symTable5, out5, exampleProgram5, fileTable5);
                    controller.getRepo().add(initialState5);
                    if(ok==true){
                        System.out.println("-------------------------------------\n");
                        ok=false;
                    }
                    executeProgram();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    @Override
    public void executeProgram() {
        try {
            controller.allStep();
        } catch (ControllerException e) {
            System.out.println("Error during execution: " + e.getMessage());
        }
    }
}
 */