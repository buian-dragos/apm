package controller;
import repository.IRepository;
import model.MyException;
import model.PrgState;
import model.ADT.MyIStack;
import model.stmt.IStmt;

import java.util.List;

public class Controller {
    private IRepository repository;
    public Controller(IRepository repository) {
        this.repository = repository;
    }
    public IRepository getRepository() {
        return repository;
    }
    public void setRepository(IRepository repository) {
        this.repository = repository;
    }
    public List<PrgState> getProgramStates(){
        return repository.getPrgList();
    }
    public PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> exeStack = state.getExeStack();
        if (exeStack.isEmpty()) throw new MyException("prgstate stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws MyException {
        PrgState prg = repository.getCrtPrg();
        displayPrgState(prg);
        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);
            displayPrgState(prg);
        }
    }
    public void displayPrgState(PrgState state) {
        System.out.println(state);
    }
}
