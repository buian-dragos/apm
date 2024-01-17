package model.exp;

import model.ADT.IHeap;
import model.ADT.MyIDictionary;
import model.MyException;
import model.type.Type;
import model.value.Value;

public class VarExp implements Exp{
    private String id;
    public VarExp(String i) {id=i;}
    public String getId() {return id;}
    public String setId(String i) {return id=i;}
    public String toString() {
        return "VarExp{" +
                "id='" + id + '\'' +
            '}';
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, IHeap heap) throws MyException {
        return tbl.lookup(id);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }

}
