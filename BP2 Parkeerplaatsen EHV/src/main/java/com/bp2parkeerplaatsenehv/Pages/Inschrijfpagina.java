package com.bp2parkeerplaatsenehv.Pages;

import com.bp2parkeerplaatsenehv.controllers.Data.DatabaseHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.Statement;

public class Inschrijfpagina {
    public Inschrijfpagina(Pane p) {
        // Create the main layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label infoLabel = new Label("Vul hieronder uw gegevens in om zich aan te melden in het systeem!");
        GridPane.setConstraints(infoLabel, 0, 0, 2, 1); // Span across two columns

        // Standard text fields
        TextField naamField = new TextField();
        naamField.setPromptText("Naam");
        GridPane.setConstraints(naamField, 0, 1, 2, 1);

        TextField kentekenField = new TextField();
        kentekenField.setPromptText("Kenteken");
        GridPane.setConstraints(kentekenField, 0, 2, 2, 1);

        // Additional fields for private
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        GridPane.setConstraints(emailField, 0, 4, 2, 1); // Span across two columns
        emailField.setVisible(false);

        TextField kvkNummerField = new TextField();
        kvkNummerField.setPromptText("KVK Nummer");
        GridPane.setConstraints(kvkNummerField, 0, 5, 2, 1);
        kvkNummerField.setVisible(false);

        // Checkboxes for private and company
        CheckBox privateCheckBox = new CheckBox("Particulier");
        GridPane.setConstraints(privateCheckBox, 0, 3);

        CheckBox companyCheckBox = new CheckBox("Zakelijk");
        GridPane.setConstraints(companyCheckBox, 1, 3);

        // Submit button
        Button submitButton = new Button("Aanmelden");
        GridPane.setConstraints(submitButton, 0, 6, 2, 1);

        // Event handlers for checkboxes
        privateCheckBox.setOnAction(e -> {
            if (privateCheckBox.isSelected()) {
                companyCheckBox.setSelected(false);
                emailField.setVisible(true);
                kvkNummerField.setVisible(false);
            } else {
                emailField.setVisible(false);
            }
        });

        companyCheckBox.setOnAction(e -> {
            if (companyCheckBox.isSelected()) {
                privateCheckBox.setSelected(false);
                emailField.setVisible(false);
                kvkNummerField.setVisible(true);
            } else {
                kvkNummerField.setVisible(false);
            }
        });

        submitButton.setOnAction(e -> {
            naamField.clear();
            kentekenField.clear();
            emailField.clear();
            kvkNummerField.clear();
            // implement the logic to save the data -> class databasehandler
            try {
                Connection connection = DatabaseHandler.getConnection();
                Statement statement = connection.createStatement();
                String queryParticulier = "INSERT INTO ParticuliereKlanten (naam, kenteken, email) VALUES ('" + naamField.getText() + "', '" + kentekenField.getText() + "', '" + emailField.getText() + "')";
                String queryZakelijk = "INSERT INTO ZakelijkeKlanten (naam, kenteken, kvkNummer) VALUES ('" + naamField.getText() + "', '" + kentekenField.getText() + "', '" + kvkNummerField.getText() + "')";
                if (privateCheckBox.isSelected()) {
                    statement.executeUpdate(queryParticulier);
                } else if (companyCheckBox.isSelected()) {
                    statement.executeUpdate(queryZakelijk);
                }
            } catch (Exception ex) {
                System.out.println("An error occurred while saving the data: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        // Add all elements to the grid
        grid.getChildren().addAll(infoLabel, naamField, kentekenField, privateCheckBox, companyCheckBox, emailField, kvkNummerField, submitButton);

        // Add the grid to the provided Pane
        p.getChildren().add(grid);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}