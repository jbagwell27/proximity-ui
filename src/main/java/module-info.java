module com.example.proximityui {
//    requires javafx.controls;
//    requires javafx.fxml;
//    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.fxml;

    opens code.proximityui to javafx.fxml;
    exports code.proximityui;
}