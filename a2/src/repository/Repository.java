package repository;

import model.PrgState;

import java.util.List;

public class Repository implements IRepository{
    private List<PrgState> prgList;
    public Repository(List<PrgState> p) {
        prgList = p;
    }

    @Override
    public List<PrgState> getPrgList() {
        return this.prgList;
    }

    @Override
    public PrgState getCrtPrg() {
        return this.prgList.get(0);
    }
    @Override
    public void addPrg(PrgState prg) {
        this.prgList.add(prg);
    }

}
