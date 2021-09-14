module com.origamieditor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.nyarstot.origamieditor to javafx.fxml;
    exports com.nyarstot.origamieditor;
    exports com.nyarstot.origamieditor.logic;
    opens com.nyarstot.origamieditor.logic to javafx.fxml;
}