package com.bp2parkeerplaatsenehv.Pages;

import com.bp2parkeerplaatsenehv.controllers.Data.DatabaseHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InvoerenBeschikbaarheidParkeerplaats {
    private TextField tijdslotField = new TextField();
    private TextField datumField = new TextField();
    private Button sendButton = new Button("Send");

    public InvoerenBeschikbaarheidParkeerplaats(Pane pane) {
        VBox vbox = new VBox(10);
        Label label = new Label("Op deze pagina kunt u als beheerder nieuwe/basisbeschikbaarheid van parkeerplaatsen aanmaken.");

        tijdslotField.setPromptText("Enter timeslot (e.g. 12:00-13:00)");
        datumField.setPromptText("Vul datum in: YYYY-MM-DD");

        sendButton.setOnAction(event -> addBeschikbaarheid());

        vbox.getChildren().addAll(label ,tijdslotField, datumField, sendButton);
        pane.getChildren().add(vbox);
    }

    private void addBeschikbaarheid() {
        String tijdslot = tijdslotField.getText();
        String datum = datumField.getText();

        if (tijdslot.isEmpty() || datum.isEmpty()) {
            System.out.println("Both fields must be filled out.");
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
            datumField.clear();
        } catch (Exception e) {
            showAlert("Ongeldige beschikbaarheid of al bekend.");
            e.printStackTrace();
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