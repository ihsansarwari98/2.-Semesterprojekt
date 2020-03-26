module com.mycompany.tv2guitest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires postgresql;

    opens com.mycompany.tv2guitest to javafx.fxml;
    exports com.mycompany.tv2guitest;
}