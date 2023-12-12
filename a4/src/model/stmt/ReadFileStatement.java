package model.stmt;
import model.ADT.*;
import model.MyException;
import model.PrgState;
import model.type.*;
import model.value.*;
import model.exp.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadFileStatement implements IStmt{
    private Exp exppression;
    private String variable_name;
    public ReadFileStatement(Exp exppression, String variable_name){
        this.exppression = exppression;
        this.variable_name = variable_name;
    }
    public Exp getExpression(){
        return this.exppression;
    }
    public void setVariableName(String variable_name){
        this.variable_name = variable_name;
    }
    public void setExpression(Exp exppression){
        this.exppression = exppression;
    }
    public String getVariableName(){
        return this.variable_name;
    }
    @Override
    public String toString(){
        return "ReadFileStatement(" +
                "expression=" + this.exppression.toString() +
                ", variable_name=" + this.variable_name +'\'' +
                ')';
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
       MyIDictionary<String, Value> symTable = state.getSymTable();
       MyIFileTable<String, BufferedReader> fileTable = state.getFileTable();
       MyIStack<IStmt> stack = state.getExeStack();

       if(symTable.isDefined(variable_name))
       {
           Value value = symTable.lookup(variable_name);
           Type type = value.getType();
           if(type.equals(new IntType())){
               StringValue stringValue = (StringValue)exppression.eval(symTable, state.getHeap());
                String filename = stringValue.getValue();
                BufferedReader bufferedReader = fileTable.lookup(filename);
                try{
                    String line = bufferedReader.readLine();
                    IntValue intValue;
                    if(line == null){
                        intValue = new IntValue(0);
                    }
                    else{
                        intValue = new IntValue(Integer.parseInt(line));
                    }
                    symTable.update(variable_name, intValue);
                }
                catch(IOException e){
                    throw new MyException(e.getMessage());
                }

           }
           else{
               throw new MyException("Variable name is not of type int");
           }
       }
       return null;
    }
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = getExpression().typeCheck(typeEnv);
        Type typeVar = typeEnv.lookup(variable_name);
        if (! (typeExp.equals(new StringType()))) {
            throw new MyException("read file statement: expression type must be string!");
        }
        if (! (typeVar.equals(new IntType()))) {
            throw new MyException("read file statement: variable type must be integer!");
        }
        return typeEnv;
    }
}
