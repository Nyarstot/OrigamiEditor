module com.origamieditor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.origamieditor to javafx.fxml;
    exports com.origamieditor;
}