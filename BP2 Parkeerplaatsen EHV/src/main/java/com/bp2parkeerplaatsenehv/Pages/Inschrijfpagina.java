package com.bp2parkeerplaatsenehv.Pages;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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
        });

        // Add all elements to the grid
        grid.getChildren().addAll(infoLabel, naamField, kentekenField, privateCheckBox, companyCheckBox, emailField, kvkNummerField, submitButton);

        // Add the grid to the provided Pane
        p.getChildren().add(grid);
    }
}