package gui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import view.Command;

import java.util.List;

public class SelectWindow {

    @FXML
    private ListView<String> listView;

    private Stage stage;
    private List<Command> commands;

    public void setCommands(List<Command> commands) {
        this.commands = commands;
        listView.getItems().clear();
        for (Command command : commands) {
            listView.getItems().add(command.getDescription());
        }
    }

    @FXML
    private void initialize() {
        // Initialization
    }

    @FXML
    public void handleItemSelection() {
        Command selectedCommand = commands.get(listView.getSelectionModel().getSelectedIndex());
        if (selectedCommand != null) {
            MainWindow.show(selectedCommand);
        }
    }
}
