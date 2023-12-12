package controller;
import model.ADT.MyIList;
import model.value.RefValue;
import model.value.Value;
import repository.IRepository;
import model.MyException;
import model.PrgState;
import model.ADT.MyIStack;
import model.stmt.IStmt;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap) {
        List<Integer> heapAddr = getAddrFromHeap(heap.values());
        return heap.entrySet().stream()
                .filter(e -> (symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddrFromSymTable(Collection<Value> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    private List<Integer> getAddrFromHeap(Collection<Value> heapValues) {
        return heapValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
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
            Map<Integer, Value> updatedHeap = safeGarbageCollector(
                    getAddrFromSymTable(prg.getSymTable().getContent().values()),
                    prg.getHeap().getContent());

            prg.getHeap().setContent((HashMap<Integer, Value>) updatedHeap);
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
