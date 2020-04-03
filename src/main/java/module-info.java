module com.mycompany.creditsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires postgresql;

    opens com.mycompany.creditsystem to javafx.fxml, javafx.graphics;
    exports com.mycompany.creditsystem;
}