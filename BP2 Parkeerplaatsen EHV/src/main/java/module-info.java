module com.bp2parkeerplaatsenehv {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bp2parkeerplaatsenehv to javafx.fxml;
    exports com.bp2parkeerplaatsenehv;
    exports com.bp2parkeerplaatsenehv.controllers;
    opens com.bp2parkeerplaatsenehv.controllers to javafx.fxml;
}