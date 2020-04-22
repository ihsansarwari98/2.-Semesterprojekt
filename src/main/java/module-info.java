module com.mycompany.creditsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires postgresql;


    opens com.mycompany.creditsystem.presentation to javafx.fxml, javafx.graphics, javafx.controls;
    exports com.mycompany.creditsystem.presentation;
}