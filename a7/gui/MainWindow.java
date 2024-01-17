package gui;

import controller.Controller;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ADT.*;
import model.MyException;
import model.PrgState;
import model.stmt.IStmt;
import model.value.Value;
import view.Command;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MainWindow {

    @FXML
    public ListView outListView;
    @FXML
    public ListView identifiersPrgState;
    public ListView exeStackView;
    @FXML
    TextField numberPrgStates;

    @FXML
    private TableView<Map.Entry<Integer, Value>> heapTableView;
    @FXML
    private TableColumn<Map.Entry<Integer, Value>, Integer> addressColumn;
    @FXML
    private TableColumn<Map.Entry<Integer, Value>, Value> valueColumn;
    @FXML
    private ListView<String> fileListView;

    @FXML
    private TableView<Map.Entry<String, Value>> symTableView;
    @FXML
    private TableColumn<Map.Entry<String, Value>, String> varNameColumn;
    @FXML
    private TableColumn<Map.Entry<String, Value>, Value> varValueColumn;



    private Command selectedCommand;

    private Controller ctr;
    private boolean flg = true;


    public void setSelectedCommand(Command selectedCommand) {
        this.selectedCommand = selectedCommand;
        this.ctr = selectedCommand.getController();
        updateAll();
    }

    private void updateAll(){
        updateNumberPrgStates();
        identifiersPrgState.getItems().clear();

        ctr.getPrgList().forEach(prg -> {
            updateHeapTableView(prg.getHeap());
            updateOutListView(prg.getOut());
            updateFileListView(prg.getFileTable());
            updateIds(prg.getId());
        });

    }

    public void updateFileListView(MyIFileTable<String, BufferedReader> fileTable) {
        fileListView.getItems().clear();

        for (Map.Entry<String, BufferedReader> entry : fileTable.getContent().entrySet()) {
            fileListView.getItems().add(entry.getKey());
        }
    }

    private void updateNumberPrgStates() {
        if (selectedCommand != null && ctr != null) {
            int number = ctr.numberPrgStates();
            if (number == 0 && flg){
                flg = false;
                exeStackView.getItems().clear();
                symTableView.getItems().clear();
                updateOutListView(ctr.getFinalOut());
            }
            numberPrgStates.setText(Integer.toString(number));
        }
    }

    public void updateHeapTableView(IHeap heap) {
        addressColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getKey()));
        valueColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue()));

        ObservableList<Map.Entry<Integer, Value>> heapList = FXCollections.observableArrayList(heap.getContent().entrySet());

        heapTableView.setItems(heapList);
    }

    public void updateIds(int id){

        identifiersPrgState.getItems().add(id);
    }

    public void updateOutListView(MyIList outList) {
        outListView.getItems().clear();
        outListView.getItems().addAll(outList.getList());
    }

    public void updateSymTable(MyIDictionary<String, Value> symTable) {
        varNameColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getKey()));
        varValueColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getValue()));

        symTableView.getItems().clear();
        symTableView.getItems().addAll(symTable.getContent().entrySet());
    }

    public void updateExe(MyIStack<IStmt> exeStack){
//        System.out.println(exeStack.toString());
        exeStackView.getItems().clear();
        Stack<IStmt> eSt = exeStack.getStack();
//        System.out.println(eSt.size());
        for (Object elem : eSt){
//            System.out.println(elem.toString());
            exeStackView.getItems().add(elem.toString());
        }
    }



    public static void show(Command command) {
        try {
            FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("MainWindow.fxml"));
            Parent root = loader.load();

            MainWindow controller = loader.getController();


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Main Window");


            controller.setSelectedCommand(command);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRunOneStep(){
        try {
            ctr.step();
        } catch (InterruptedException e) {
            System.out.println(e);;
        }
        updateAll();
    }

    @FXML
    private void handleIdSelection(){
        int id;
        if (identifiersPrgState.getSelectionModel().getSelectedItem() != null)
            id = Integer.parseInt(identifiersPrgState.getSelectionModel().getSelectedItem().toString());
        else {
            id = -1;
        }
        ctr.getPrgList().forEach(prg -> {
            if (prg.getId() == id) {
//                System.out.println(prg.getSymTable().toString());
                updateSymTable(prg.getSymTable());
                updateExe(prg.getExeStack());
            }
        });
    }

}
