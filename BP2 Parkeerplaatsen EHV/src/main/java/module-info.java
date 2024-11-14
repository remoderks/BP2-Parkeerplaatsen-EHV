module com.bp2parkeerplaatsenehv {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires com.google.gson;


    opens com.bp2parkeerplaatsenehv to javafx.fxml;
    exports com.bp2parkeerplaatsenehv;
    exports com.bp2parkeerplaatsenehv.Model;
}