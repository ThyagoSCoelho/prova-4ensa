module com.livraria.prova {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens com.livraria.prova to javafx.fxml;
    exports com.livraria.prova;
    exports com.livraria.controller;
    opens com.livraria.controller to javafx.fxml;
}