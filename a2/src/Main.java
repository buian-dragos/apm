import java.util.Scanner;
import java.util.List;

import controller.Controller;
import model.ADT.*;
import model.MyException;
import repository.Repository;
import model.PrgState;
import model.exp.*;
import model.stmt.*;
import model.type.*;
import model.value.*;
import repository.IRepository;
import view.View;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a program (1, 2, or 3):");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                example1();
                break;
            case 2:
                example2();
                break;
            case 3:
                example3();
                break;
            default:
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
        }
    }

    private static void example1() {
        //int v; v=2; Print(v)
        IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()), new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        MyIStack<IStmt> stk1 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl1 = new MyDictionary<String,Value>();
        MyIList<Value> out1 = new MyList<Value>();

        PrgState prg1 = new PrgState(stk1,symtbl1,out1,ex1);
        List<PrgState> l1 = List.of(prg1);
        IRepository repo1 = new Repository(l1);
        Controller ctr1 = new Controller(repo1);
        View view1 = new View(ctr1);
        try {
            view1.run();
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void example2() {
        //int a;int b; a=2+3*5;b=a+1;Print(b)
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()), new CompStmt(new AssignStmt("a", new ArithExp("+",new ValueExp(new IntValue(2)),new ArithExp("*",new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b",new ArithExp("+",new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        MyIStack<IStmt> stk2 = new MyStack<>();
        MyIDictionary<String, Value> symtbl2 = new MyDictionary<>();
        MyIList<Value> out2 = new MyList<>();

        PrgState prg2 = new PrgState(stk2, symtbl2, out2, ex2);
        List<PrgState> myPrgList2 = List.of(prg2);
        IRepository repo2 = new Repository(myPrgList2);
        Controller ctr2 = new Controller(repo2);
        View view2 = new View(ctr2);
        try {
            view2.run();
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void example3() {
        //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        MyIStack<IStmt> stk3 = new MyStack<>();
        MyIDictionary<String, Value> symtbl3 = new MyDictionary<>();
        MyIList<Value> out3 = new MyList<>();

        PrgState prgState3 = new PrgState(stk3, symtbl3, out3, ex3);
        List<PrgState> myPrgList3 = List.of(prgState3);
        IRepository repo3 = new Repository(myPrgList3);
        Controller ctr3 = new Controller(repo3);
        View view3 = new View(ctr3);
        try {
            view3.run();
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
