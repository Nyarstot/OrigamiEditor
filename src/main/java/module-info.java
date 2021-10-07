module com.origamieditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.fxmisc.richtext;
    requires reactfx;
    requires java.xml;


    opens com.nyarstot.origamieditor to javafx.fxml;
    exports com.nyarstot.origamieditor;
    exports com.nyarstot.origamieditor.logic;
    opens com.nyarstot.origamieditor.logic to javafx.fxml;
}