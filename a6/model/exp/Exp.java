package model.exp;
import model.ADT.IHeap;
import model.ADT.MyIDictionary;
import model.MyException;
import model.type.Type;
import model.value.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> symTbl, IHeap heap) throws MyException;
    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
