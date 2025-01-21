package com.bp2parkeerplaatsenehv.Pages;

import com.bp2parkeerplaatsenehv.controllers.Data.DatabaseHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        TextField nameField = new TextField();
        nameField.setPromptText("Naam");
        GridPane.setConstraints(nameField, 0, 1, 2, 1);
        TextField licensePlateField = new TextField();
        licensePlateField.setPromptText("Kenteken");
        GridPane.setConstraints(licensePlateField, 0, 2, 2, 1);

        // Additional fields for private
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        GridPane.setConstraints(emailField, 0, 4, 2, 1); // Span across two columns
        emailField.setVisible(false);
        TextField kvkField = new TextField();
        kvkField.setPromptText("KVK Nummer");
        GridPane.setConstraints(kvkField, 0, 5, 2, 1);
        kvkField.setVisible(false);

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
                kvkField.setVisible(false);
            } else {
                emailField.setVisible(false);
            }
        });


        companyCheckBox.setOnAction(e -> {
            if (companyCheckBox.isSelected()) {
                privateCheckBox.setSelected(false);
                emailField.setVisible(false);
                kvkField.setVisible(true);
            } else {
                kvkField.setVisible(false);
            }
        });

        submitButton.setOnAction(e -> {
            // Validate if fields not empty
            if (nameField.getText().isEmpty() || licensePlateField.getText().isEmpty() ||
                    (privateCheckBox.isSelected() && emailField.getText().isEmpty()) ||
                    (companyCheckBox.isSelected() && kvkField.getText().isEmpty())) {
                showAlert("Alle velden moeten worden ingevuld.");
                return;
            }
            // Validate name (only used letters and spaces)
            if (!isNameValid(nameField.getText())) {
                showAlert("Naam mag alleen letters en spaties bevatten.");
                return;
            }
            // Validate license plate
            if (!isLicensePlateValid(licensePlateField.getText())) {
                showAlert("Fout. Kenteken moet in het formaat X-XXX-X of XX-XX-XX zijn. Mag alleen letters en cijfers bevatten.");
                return;
            }
            // Validate email (only if the email field is visible and applicable)
            if (emailField.isVisible() && !isEmailValid(emailField.getText())) {
                showAlert("Fout. Email moet in het formaat X@X.X zijn.");
                return;
            }
            // Validate KVK number (only if the KVK field is visible and applicable)
            // checks if the KVK number is between 1 and 8 digits.
            if (kvkField.isVisible() && !kvkField.getText().matches("[0-9]{1,8}")) {
                showAlert("Fout. KVK nummer moet 8 cijfers bevatten.");
                return;
            }
            // Database insert logic. If statement used to check whether the user is a private or company customer.
            // Exception error for duplicate key and general errors.
            try (Connection connection = DatabaseHandler.getConnection()) {
                if (privateCheckBox.isSelected()) {
                    String query = "INSERT INTO ParticuliereKlanten (naam, kenteken, email) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, nameField.getText());
                    preparedStatement.setString(2, licensePlateField.getText());
                    preparedStatement.setString(3, emailField.getText());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    showAlert("U bent succesvol aangemeld als particuliere klant!");
                } else if (companyCheckBox.isSelected()) {
                          String query = "INSERT INTO ZakelijkeKlanten (naam, kenteken, kvknummer) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, nameField.getText());
                    preparedStatement.setString(2, licensePlateField.getText());
                    preparedStatement.setString(3, kvkField.getText());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    showAlert("U bent succesvol als zakelijke klant!");
                }
            } catch (SQLException ex) {
                if (ex.getMessage().contains("DuplicateKeyException")) {
                    showAlert("Gegevens al bekend in het systeem.");
                } else {
                    showAlert("Er is een fout opgetreden bij het aanmelden: " + ex.getMessage());
                }
                ex.printStackTrace();
            }
            nameField.clear();
            licensePlateField.clear();
            emailField.clear();
            kvkField.clear();
        });
        // Add all elements to the grid
        grid.getChildren().addAll(infoLabel, nameField, licensePlateField, privateCheckBox, companyCheckBox, emailField, kvkField, submitButton);
        // Add the grid to the provided Pane
        p.getChildren().add(grid);
    }

    public boolean isNameValid(String name) {
        return name.matches("[a-zA-Z\\s]+");
    }

    // Checks whether the license plate consists of letters, numbers and dashes only.
    // Also checks if the license plate is in the correct format. X-XXX-X, XX-XX-XX, X-XXX-XX, XXX-XX-X, XX-XXX-X
    public boolean isLicensePlateValid(String licensePlate) {
        return licensePlate.matches("[A-Z0-9]-[A-Z0-9]{3}-[A-Z0-9]") || licensePlate.matches("[A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2}") ||
                licensePlate.matches("[A-Z0-9]-[A-Z0-9]{3}-[A-Z0-9]{2}") || licensePlate.matches("[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]") ||
                licensePlate.matches("[A-Z0-9]{2}-[A-Z0-9]{3}-[A-Z0-9]");
    }

    /* Checks whether the email is in the correct format. X@X.X
       [a-zA-Z0-9]+: One or more alphanumeric characters before the @ symbol.
       @: The @ symbol.
       [a-zA-Z0-9]+: One or more alphanumeric characters after the @ symbol and before the . symbol.
       .: The . symbol.
       [a-zA-Z0-9]: One alphanumeric character after the . symbol.
    */
    public boolean isEmailValid(String email) {
        return email.matches("[a-zA-Z0-9_.-]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}