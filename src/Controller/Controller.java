package Controller;
import repository.IRepository;
import model.state.ProgramState;
import exceptions.ControllerException;
import model.utils.GarbageCollector;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Collection;

public class Controller implements IController {
    private IRepository repo;
    boolean flag;
    ExecutorService executor;

    public Controller(IRepository repo) {
        this.repo = repo;
        flag = true;
        this.executor = Executors.newFixedThreadPool(2);
    }

    private int myOption;
    ///Old allStep method
    /*
    public void allStep() {
        ProgramState state = repo.getCurrent();
        displayCurrent();
        while (!state.getExeStack().isEmpty()) {
            oneStep(state);
            state.getHeap().setContent(GarbageCollector.unsafeGarbageCollector(
                    state.getSymTable().getContent().values(), state.getHeap().getContent()));
            if (state.getExeStack().isEmpty() && flag == false) {
                ProgramState output = repo.getCurrent();
                System.out.println("--------------------\nFINAL OUTPUT IS : " + output.getOut() + "--------------------\n\n");
            } else {
                displayCurrent();
            }
        }
    }
    */

    public void allStep() {
        executor = Executors.newFixedThreadPool(2);
        // Remove the completed programs
        List<ProgramState> prgList = removeCompletedPrg(repo.getPrgList());
        while (prgList.size() > 0) {
            List<ProgramState> finalPrgList = prgList;
            // Call conservativeGarbageCollector
            prgList.forEach(prg -> prg.getHeap().setContent(
                    GarbageCollector.conservativeGarbageCollector(
                            finalPrgList.stream()
                                    .map(p -> p.getSymTable().getContent().values())
                                    .flatMap(Collection::stream)
                                    .collect(Collectors.toList()),
                            prg.getHeap().getContent()
                    )
            ));
            oneStepForAllPrg(prgList);
            // Remove the completed programs
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);
    }


    public ProgramState oneStep(ProgramState state) throws ControllerException {
        try {
            return state.oneStep();
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    public void oneStepForAllPrg(List<ProgramState> prgList) {
        //Before the execution, print the PrgState List into the log file
        //prgList.forEach(prg -> repo.logPrgStateExec(prg));

        // Prepare the list of callables
        List<Callable<ProgramState>> callList = prgList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (() -> p.oneStep()))
                .collect(Collectors.toList());

        //Start the execution of the callables
        //It returns the list of new created PrgStates (namely threads)
        List<ProgramState> newPrgList = null;
        try {
            newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            // Here you can treat the possible exceptions thrown by statements execution
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Add the new created threads to the list of existing threads
        prgList.addAll(newPrgList);

        //After the execution, print the PrgState List into the log file
        prgList.forEach(this::logPrgStateExec);

        //Save the current programs in the repository
        repo.setPrgList(prgList);
    }

    public IRepository getRepo() {return repo;}

    public void setFlag(boolean value) {
        flag = value;
    }

    public int getOption() {return myOption;}

    public List <ProgramState> removeCompletedPrg(List<ProgramState> inPrgList) {
        return inPrgList.stream().filter(ProgramState::isNotCompleted).collect(Collectors.toList());
    }

    public void logPrgStateExec(ProgramState p)
    {
        String stackText = p.getExeStack().toString();
        String symTableText = p.getSymTable().toString();
        String fileTableText = p.getFileTable().toString();
        String heapText = p.getHeap().toString();
        String outText=p.getOut().toString();
        String prgStateIDText = Integer.toString(p.getId());
        repo.logPrgStateExec(p, stackText, symTableText, fileTableText, heapText, prgStateIDText, outText);
    }
}