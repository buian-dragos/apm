package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ADT.*;
import model.PrgState;
import model.exp.*;
import model.stmt.*;
import model.type.*;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;
import repository.IRepository;
import repository.Repository;
import view.Command;
import view.ExitCommand;
import view.RunExample;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        List<Command> commands = new ArrayList<>();
        commands.add(new ExitCommand("0", "exit"));
        //int v; v=2;Print(v)
        IStmt ex1 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        ex1.typeCheck(new MyDictionary<String, Type>());
        MyIStack<IStmt> stk1 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl1 = new MyDictionary<String,Value>();
        MyIList<Value> out1 = new MyList<Value>();
        MyIFileTable<String, BufferedReader> fileTable1 = new MyFileTable<String, BufferedReader>();
        IHeap h1 = new Heap();

        PrgState prg1 = new PrgState(stk1,symtbl1,out1,fileTable1,h1,ex1);
        List<PrgState> l1 = List.of(prg1);
        IRepository repo1 = new Repository(l1,"log1.txt");
        Controller ctr1 = new Controller(repo1);
        commands.add(new RunExample("1", ex1.toString(), ctr1));

        //int a;int b; a=2+3*5;b=a+1;Print(b)
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp("+",new ValueExp(new IntValue(2)),
                                new ArithExp("*",new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp("+",new VarExp("a"),
                                        new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        ex2.typeCheck(new MyDictionary<String, Type>());
        MyIStack<IStmt> stk2 = new MyStack<>();
        MyIDictionary<String, Value> symtbl2 = new MyDictionary<>();
        MyIList<Value> out2 = new MyList<>();
        MyIFileTable<String, BufferedReader> fileTable2 = new MyFileTable<>();
        IHeap h2 = new Heap();

        PrgState prg2 = new PrgState(stk2, symtbl2, out2,fileTable2,h2, ex2);
        List<PrgState> myPrgList2 = List.of(prg2);
        IRepository repo2 = new Repository(myPrgList2, "log2.txt");
        Controller ctr2 = new Controller(repo2);
        commands.add(new RunExample("2", ex2.toString(), ctr2));


        //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",
                                        new ValueExp(new IntValue(2))), new AssignStmt("v",
                                        new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        ex3.typeCheck(new MyDictionary<String, Type>());
        MyIStack<IStmt> stk3 = new MyStack<>();
        MyIDictionary<String, Value> symtbl3 = new MyDictionary<>();
        MyIList<Value> out3 = new MyList<>();
        MyIFileTable<String, BufferedReader> fileTable3 = new MyFileTable<>();
        IHeap h3 = new Heap();

        PrgState prgState3 = new PrgState(stk3, symtbl3, out3,fileTable3,h3, ex3);
        List<PrgState> myPrgList3 = List.of(prgState3);
        IRepository repo3 = new Repository(myPrgList3, "log3.txt");
        Controller ctr3 = new Controller(repo3);
        commands.add(new RunExample("3", ex3.toString(), ctr3));

        //THE READ FROM FILE EXAMPLE:
        //string varf; varf="test.in"; openRFile(varf); int varc; readFile(varf,varc);print(varc); readFile(varf,varc); print(varc) closeRFile(varf)
        IStmt example4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new OpenReadFileStatement(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFileStatement(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFileStatement(
                                                                new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseReadFileStatement(
                                                                                new VarExp("varf"))))))))));

        example4.typeCheck(new MyDictionary<String, Type>());
        MyIStack<IStmt> stk4 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl4 = new MyDictionary<String,Value>();
        MyIList<Value> out4 = new MyList<Value>();
        MyIFileTable<String, BufferedReader> fileTable4 = new MyFileTable<String, BufferedReader>();
        IHeap h4 = new Heap();

        PrgState prg4 = new PrgState(stk4,symtbl4,out4,fileTable4,h4,example4);
        List<PrgState> l4 = List.of(prg4);
        IRepository repo4 = new Repository(l4,"log4.txt");
        Controller ctr4 = new Controller(repo4);
        commands.add(new RunExample("4", example4.toString(), ctr4));


        // Ref int v; new(v,20); Ref Ref int a; new(a,v); print(v); print(a)

        IStmt ex5 = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(
                                new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(
                                        new NewStmt("a", new VarExp("v")),
                                        new CompStmt(
                                                new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a"))
                                        )
                                )
                        )
                )
        );

        ex5.typeCheck(new MyDictionary<String, Type>());
        MyIStack<IStmt> stk5 = new MyStack<>();
        MyIDictionary<String, Value> symtbl5 = new MyDictionary<>();
        MyIList<Value> out5 = new MyList<>();
        MyIFileTable<String, BufferedReader> fileTable5 = new MyFileTable<>();
        IHeap h5 = new Heap();

        PrgState prg5 = new PrgState(stk5, symtbl5, out5, fileTable5, h5, ex5);
        List<PrgState> l5 = List.of(prg5);
        IRepository repo5 = new Repository(l5, "log5.txt");
        Controller ctr5 = new Controller(repo5);
        commands.add(new RunExample("5", ex5.toString(), ctr5));


        // Ref int v; new(v,20); Ref Ref int a; new(a,v); print(rH(v)); print(rH(rH(a))+5)
        IStmt ex6 = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(
                                new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(
                                        new NewStmt("a", new VarExp("v")),
                                        new CompStmt(
                                                new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                new PrintStmt(
                                                        new ArithExp("+",
                                                                new ReadHeapExp(new ReadHeapExp(new VarExp("a"))),
                                                                new ValueExp(new IntValue(5))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        ex6.typeCheck(new MyDictionary<String, Type>());
        MyIStack<IStmt> stk6 = new MyStack<>();
        MyIDictionary<String, Value> symtbl6 = new MyDictionary<>();
        MyIList<Value> out6 = new MyList<>();
        MyIFileTable<String, BufferedReader> fileTable6 = new MyFileTable<>();
        IHeap h6 = new Heap();

        PrgState prg6 = new PrgState(stk6, symtbl6, out6, fileTable6, h6, ex6);
        List<PrgState> l6 = List.of(prg6);
        IRepository repo6 = new Repository(l6, "log6.txt");
        Controller ctr6 = new Controller(repo6);
        commands.add(new RunExample("6", ex6.toString(), ctr6));

        // Ref int v; new(v,20); print(rH(v)); wH(v,30); print(rH(v)+5);
        IStmt ex7 = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(
                                new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                new CompStmt(
                                        new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(
                                                new ArithExp("+",
                                                        new ReadHeapExp(new VarExp("v")),
                                                        new ValueExp(new IntValue(5))
                                                )
                                        )
                                )
                        )
                )
        );


        ex7.typeCheck(new MyDictionary<String, Type>());
        MyIStack<IStmt> stk7 = new MyStack<>();
        MyIDictionary<String, Value> symtbl7 = new MyDictionary<>();
        MyIList<Value> out7 = new MyList<>();
        MyIFileTable<String, BufferedReader> fileTable7 = new MyFileTable<>();
        IHeap h7 = new Heap();

        PrgState prg7 = new PrgState(stk7, symtbl7, out7, fileTable7, h7, ex7);
        List<PrgState> l7 = List.of(prg7);
        IRepository repo7 = new Repository(l7, "log7.txt");
        Controller ctr7 = new Controller(repo7);
        commands.add(new RunExample("7", ex7.toString(), ctr7));

        // Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); print(rH(rH(a)))
        IStmt ex8 = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(
                                new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(
                                        new NewStmt("a", new VarExp("v")),
                                        new CompStmt(
                                                new NewStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a"))))
                                        )
                                )
                        )
                )
        );


        ex8.typeCheck(new MyDictionary<String, Type>());
        MyIStack<IStmt> stk8 = new MyStack<>();
        MyIDictionary<String, Value> symtbl8 = new MyDictionary<>();
        MyIList<Value> out8 = new MyList<>();
        MyIFileTable<String, BufferedReader> fileTable8 = new MyFileTable<>();
        IHeap h8 = new Heap();

        PrgState prg8 = new PrgState(stk8, symtbl8, out8, fileTable8, h8, ex8);
        List<PrgState> l8 = List.of(prg8);
        IRepository repo8 = new Repository(l8, "log8.txt");
        Controller ctr8 = new Controller(repo8);
        commands.add(new RunExample("8", ex8.toString(), ctr8));

        // int v; v=4; (while (v>0) print(v); v=v-1); print(v)
        IStmt ex9 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(
                                new WhileStmt(
                                        new RelationExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                                        new CompStmt(
                                                new PrintStmt(new VarExp("v")),
                                                new AssignStmt("v", new ArithExp("-", new VarExp("v"), new ValueExp(new IntValue(1))))
                                        )
                                ),
                                new PrintStmt(new VarExp("v"))
                        )
                )
        );
        ex9.typeCheck(new MyDictionary<String, Type>());
        MyIStack<IStmt> stk9 = new MyStack<>();
        MyIDictionary<String, Value> symtbl9 = new MyDictionary<>();
        MyIList<Value> out9 = new MyList<>();
        MyIFileTable<String, BufferedReader> fileTable9 = new MyFileTable<>();
        IHeap h9 = new Heap();

        PrgState prg9 = new PrgState(stk9, symtbl9, out9, fileTable9, h9, ex9);
        List<PrgState> l9 = List.of(prg9);
        IRepository repo9 = new Repository(l9, "log9.txt");
        Controller ctr9 = new Controller(repo9);
        commands.add(new RunExample("9", ex9.toString(), ctr9));

        IStmt ex10 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(
                                new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(
                                        new NewStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(
                                                new ForkStmt(
                                                        new CompStmt(
                                                                new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
                                                                new CompStmt(
                                                                        new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                        new CompStmt(
                                                                                new PrintStmt(new VarExp("v")),
                                                                                new PrintStmt(new ReadHeapExp(new VarExp("a")))
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompStmt(
                                                        new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new ReadHeapExp(new VarExp("a")))
                                                )
                                        )
                                )
                        )
                )
        );
        ex10.typeCheck(new MyDictionary<String, Type>());
        MyIStack<IStmt> stk10 = new MyStack<>();
        MyIDictionary<String, Value> symtbl10 = new MyDictionary<>();
        MyIList<Value> out10 = new MyList<>();
        MyIFileTable<String, BufferedReader> fileTable10 = new MyFileTable<>();
        IHeap h10 = new Heap();

        PrgState prg10 = new PrgState(stk10, symtbl10, out10, fileTable10, h10, ex10);
        List<PrgState> l10 = List.of(prg10);
        IRepository repo10 = new Repository(l10, "log10.txt");
        Controller ctr10 = new Controller(repo10);
        commands.add(new RunExample("10", ex10.toString(), ctr10));


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SelectWindow.fxml"));
        Parent root = loader.load();
        SelectWindow selectWindowController = loader.getController();

        selectWindowController.setCommands(commands);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Select Window");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
