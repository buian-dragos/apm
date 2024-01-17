package model;

import model.ADT.*;
import model.stmt.IStmt;
import model.value.Value;
import java.io.BufferedReader;
import java.io.PrintWriter;


public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIFileTable<String, BufferedReader> fileTable;
    IHeap heap;

    private int id;
    private static int staticId = 0;
    IStmt originalProgram; //optional field, but good to have
    public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, Value> symTable, MyIList<Value> out,MyIFileTable<String,BufferedReader> fileTable,IHeap heap ,IStmt originalProgram){
        this.exeStack=exeStack;
        this.symTable=symTable;
        this.out = out;
        this.originalProgram=originalProgram;
        this.fileTable = fileTable;
        this.exeStack.push(originalProgram);
        this.heap = heap;
        this.id = setId();
    }



    @Override
    public String toString() {
        return "PrgState{" +
                "id=" +
                ", exeStack=" + exeStack +
                ", symTable=" + symTable +
                ", out=" + out +
                ",heap=" + heap +
                '}';
    }
    public int getId(){
        return this.id;
    }

    public synchronized int setId() {
        staticId++;
        return staticId;
    }
    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }


    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }
    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }
    public MyIList<Value> getOut() {
        return out;
    }
    public MyIFileTable<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIFileTable<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }
    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }
    public void setOut(MyIList<Value> out) {
        this.out = out;
    }



    public PrgState oneStep() throws Exception {
        if(exeStack.isEmpty()) throw new Exception("Program state stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }


    public IHeap getHeap() {
        return heap;
    }

    public void setHeap(IHeap heap) {
        this.heap = heap;
    }


}
