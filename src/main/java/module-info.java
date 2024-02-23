module com.example.test2b {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.test2b to javafx.fxml;
    exports com.example.test2b;
    exports com.example.test2b.model;
    opens com.example.test2b.model to javafx.fxml;
}