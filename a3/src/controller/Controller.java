package controller;
import model.ADT.MyIList;
import model.value.Value;
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

    public MyIList<Value> getOut(PrgState state){
        return state.getOut();
    }

    public PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> exeStack = state.getExeStack();
        if (exeStack.isEmpty()) throw new MyException("prgstate stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws MyException {
        PrgState prg = repository.getCrtPrg();
        repository.logPrgStateExec(prg);
        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);
            repository.logPrgStateExec(prg);
        }
    }
    public void displayPrgState(PrgState state) {
        System.out.println(state);
    }
    private void printThings(){
        PrgState programState = repository.getCrtPrg();

        System.out.print("-----ExeStack\n");
        System.out.print(programState.getExeStack().toString() + "\n");

        System.out.print("-----OutputList\n");
        System.out.print(programState.getOut() + "\n");

        System.out.print("-----SymbolTable\n");
        System.out.print(programState.getSymTable().toString() + "\n");


        System.out.print("-----FileTable\n");
        System.out.print(programState.getFileTable().toString() + "\n");

    }
}
