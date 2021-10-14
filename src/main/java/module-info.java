module com.origamieditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.fxmisc.richtext;
    requires reactfx;
    requires java.xml;
    requires flowless;
    requires java.prefs;


    opens com.nyarstot.origamieditor to javafx.fxml;
    exports com.nyarstot.origamieditor;
    exports com.nyarstot.origamieditor.util;
    opens com.nyarstot.origamieditor.util to javafx.fxml;
    exports com.nyarstot.origamieditor.editor;
    opens com.nyarstot.origamieditor.editor to javafx.fxml;
}