module com.poo_java.testprepexamen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.poo_java.testprepexamen to javafx.fxml;
    exports com.poo_java.testprepexamen;
}