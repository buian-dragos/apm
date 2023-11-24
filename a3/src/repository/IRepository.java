package repository;

import model.PrgState;
import model.MyException;

import java.util.List;

public interface IRepository {
    public List<PrgState> getPrgList();

    PrgState getCrtPrg();

    void addPrg(PrgState prg);
    void logPrgStateExec(PrgState program) throws MyException;
}
