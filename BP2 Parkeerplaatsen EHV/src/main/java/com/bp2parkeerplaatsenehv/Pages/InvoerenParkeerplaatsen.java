package com.bp2parkeerplaatsenehv.Pages;

import com.bp2parkeerplaatsenehv.controllers.Data.DatabaseHandler;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InvoerenParkeerplaatsen {
    private TextField tijdslotField = new TextField();
    private DatePicker datumPicker = new DatePicker();
    private Button verstuurBeschikbaarheidButton = new Button("Verstuur beschikbaarheid");
    private TextField objectIDField = new TextField();
    private Button verstuurObjectIDButton = new Button("Verstuur ObjectID");

    public InvoerenParkeerplaatsen(Pane pane) {
        VBox vbox = new VBox(10);
        Label label = new Label("Op deze pagina kunt u als beheerder nieuwe/basisbeschikbaarheid van parkeerplaatsen aanmaken.");
        tijdslotField.setPromptText("vul tijdslot in. (e.g. 12:00-13:00)");
        datumPicker.setPromptText("Vul datum in: YYYY-MM-DD");
        verstuurBeschikbaarheidButton.setOnAction(event -> addBeschikbaarheid());
        objectIDField.setPromptText("Vul objectID in");
        verstuurObjectIDButton.setOnAction(event -> addObjectID());

        // Set preferred width for tijdslotField and objectIDField
        double prefWidth = 200;
        tijdslotField.setMaxWidth(prefWidth);
        datumPicker.setPrefWidth(prefWidth);
        objectIDField.setMaxWidth(prefWidth);
        verstuurBeschikbaarheidButton.setPrefWidth(prefWidth);
        verstuurObjectIDButton.setPrefWidth(prefWidth);

        vbox.getChildren().addAll(label ,tijdslotField, datumPicker, verstuurBeschikbaarheidButton, objectIDField, verstuurObjectIDButton);
        pane.getChildren().add(vbox);
    }

    // ---------------   Adds beschikbaarheid to the database ---------------------------
    private void addBeschikbaarheid() {
        String tijdslot = tijdslotField.getText();
        String datum = datumPicker.getValue().toString();

        if (tijdslot.isEmpty() || datum.isEmpty()) {
            showAlert("Alle velden moeten worden ingevuld.");
            return;
        }
        try {
            Connection connection = DatabaseHandler.getConnection();
            String query = "INSERT INTO BeschikbaarheidParkeerplaatsen (tijdslot, datum) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tijdslot);
            preparedStatement.setString(2, datum);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            showAlert("Beschikbaarheid toegevoegd.");
            tijdslotField.clear();
            datumPicker.setValue(null);
        } catch (Exception e) {
            showAlert("Ongeldige beschikbaarheid of al bekend.");
            e.printStackTrace();
        }
    }

    // ---------------   Adds objectID to the database ---------------------------
    private void addObjectID() {
        String objectID = objectIDField.getText();
        try {
            Connection connection = DatabaseHandler.getConnection();
            String query = "INSERT INTO ObjectIDs (objectID) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, objectID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            showAlert("Parkeervak toegevoegd.");
            objectIDField.clear();
        } catch (SQLException ex) {
            if (ex.getMessage().contains("DuplicateKeyException")) {
                showAlert("Gegevens al bekend in het systeem.");
            } else {
                showAlert("Er is een fout opgetreden: " + ex.getMessage());
            }
            ex.printStackTrace();
        }
    }

    private void showAlert (String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}