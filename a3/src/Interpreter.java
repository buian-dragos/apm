import model.PrgState;
import model.ADT.MyIStack;
import model.ADT.MyStack;
import model.exp.*;
import model.stmt.AssignStmt;
import model.stmt.CompStmt;
import model.stmt.IStmt;
import model.stmt.VarDeclStmt;
import model.type.StringType;
import model.value.StringValue;
import model.exp.ValueExp;
import model.ADT.MyIFileTable;
import model.ADT.MyFileTable;
import model.exp.VarExp;
import model.ADT.*;
import model.stmt.*;
import model.type.*;
import model.value.*;
import repository.*;
import controller.*;
import view.*;
import java.util.List;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String[] args){
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));

        //int v; v=2;Print(v)
        IStmt ex1 = new CompStmt(new VarDeclStmt("v",new IntType()), new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        MyIStack<IStmt> stk1 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl1 = new MyDictionary<String,Value>();
        MyIList<Value> out1 = new MyList<Value>();
        MyIFileTable<String, BufferedReader> fileTable1 = new MyFileTable<String, BufferedReader>();

        PrgState prg1 = new PrgState(stk1,symtbl1,out1,fileTable1,ex1);
        List<PrgState> l1 = List.of(prg1);
        IRepository repo1 = new Repository(l1,"log1.txt");
        Controller ctr1 = new Controller(repo1);
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));

        //int a;int b; a=2+3*5;b=a+1;Print(b)
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()), new CompStmt(new AssignStmt("a", new ArithExp("+",new ValueExp(new IntValue(2)),new ArithExp("*",new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b",new ArithExp("+",new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        MyIStack<IStmt> stk2 = new MyStack<>();
        MyIDictionary<String, Value> symtbl2 = new MyDictionary<>();
        MyIList<Value> out2 = new MyList<>();
        MyIFileTable<String, BufferedReader> fileTable2 = new MyFileTable<>();

        PrgState prg2 = new PrgState(stk2, symtbl2, out2,fileTable2, ex2);
        List<PrgState> myPrgList2 = List.of(prg2);
        IRepository repo2 = new Repository(myPrgList2, "log2.txt");
        Controller ctr2 = new Controller(repo2);
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));


        //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        MyIStack<IStmt> stk3 = new MyStack<>();
        MyIDictionary<String, Value> symtbl3 = new MyDictionary<>();
        MyIList<Value> out3 = new MyList<>();
        MyIFileTable<String, BufferedReader> fileTable3 = new MyFileTable<>();

        PrgState prgState3 = new PrgState(stk3, symtbl3, out3,fileTable3, ex3);
        List<PrgState> myPrgList3 = List.of(prgState3);
        IRepository repo3 = new Repository(myPrgList3, "log3.txt");
        Controller ctr3 = new Controller(repo3);
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));

        //THE READ FROM FILE EXAMPLE:
        //string varf; varf="test.in"; openRFile(varf); int varc; readFile(varf,varc);print(varc); readFile(varf,varc); print(varc) closeRFile(varf)
        IStmt example4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))), new CompStmt(new OpenReadFileStatement(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFileStatement(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStatement(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseReadFileStatement(new VarExp("varf"))))))))));
        MyIStack<IStmt> stk4 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl4 = new MyDictionary<String,Value>();
        MyIList<Value> out4 = new MyList<Value>();
        MyIFileTable<String, BufferedReader> fileTable4 = new MyFileTable<String, BufferedReader>();

        PrgState prg4 = new PrgState(stk4,symtbl1,out4,fileTable4,example4);
        List<PrgState> l4 = List.of(prg4);
        IRepository repo4 = new Repository(l4,"testOpenReadClose.txt");
        Controller ctr4 = new Controller(repo4);
        menu.addCommand(new RunExample("4", example4.toString(), ctr4));

        menu.show();

    }
}
