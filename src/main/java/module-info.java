module com.example.fx2022 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.example.fx2022 to javafx.fxml;
    exports com.example.fx2022;
}