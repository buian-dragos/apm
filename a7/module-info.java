module gui{
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens gui to javafx.fxml;
    exports gui;
}