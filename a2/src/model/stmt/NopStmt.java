package model.stmt;

import model.ADT.*;
import model.MyException;
import model.PrgState;
import model.type.Type;
public class NopStmt implements IStmt{
    public NopStmt(){}
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

}
