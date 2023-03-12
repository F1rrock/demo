module com.example.demotest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demotest to javafx.fxml;
    exports com.example.demotest;
}