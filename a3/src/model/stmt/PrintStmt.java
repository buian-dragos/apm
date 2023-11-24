package model.stmt;

import model.ADT.*;
import model.MyException;
import model.PrgState;
import model.exp.Exp;
import model.value.Value;

public class PrintStmt implements IStmt{
    Exp expression;
    public PrintStmt(Exp givenExpression){
        expression =givenExpression;}
    public Exp getExpression() {return expression;}
    public void setExpression(Exp expression) {this.expression = expression;}
    public String toString(){ return "print(" + expression.toString() + ")";}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> exeStack = state.getExeStack();
        MyIList<Value> out = state.getOut();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        out.add(expression.eval(symTable));
        return state;
    }


}
