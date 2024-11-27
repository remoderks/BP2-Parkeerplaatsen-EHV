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
        GridPane.setConstraints(infoLabel, 0, 0);
        // Standard text fields
        TextField naamField = new TextField();
        naamField.setPromptText("Naam");
        GridPane.setConstraints(naamField, 0, 1);

        TextField kentekenField = new TextField();
        kentekenField.setPromptText("Kenteken");
        GridPane.setConstraints(kentekenField, 0, 2);

        // Checkboxes for private and company
        CheckBox privateCheckBox = new CheckBox("Customer");
        GridPane.setConstraints(privateCheckBox, 0, 6);

        CheckBox companyCheckBox = new CheckBox("Company");
        GridPane.setConstraints(companyCheckBox, 1, 6);

        // Additional fields for private
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        GridPane.setConstraints(emailField, 0, 3);
        emailField.setVisible(false);

        // Additional fields for company
        TextField bedrijfsnaamField = new TextField();
        bedrijfsnaamField.setPromptText("Bedrijfsnaam");
        GridPane.setConstraints(bedrijfsnaamField, 0, 4);
        bedrijfsnaamField.setVisible(false);

        TextField kvkNummerField = new TextField();
        kvkNummerField.setPromptText("KVK Nummer");
        GridPane.setConstraints(kvkNummerField, 0, 5);
        kvkNummerField.setVisible(false);

        Button submitButton = new Button("Submit");
        GridPane.setConstraints(submitButton, 0, 7);

        // Event handlers for checkboxes
        privateCheckBox.setOnAction(e -> {
            if (privateCheckBox.isSelected()) {
                companyCheckBox.setSelected(false);
                emailField.setVisible(true);
                bedrijfsnaamField.setVisible(false);
                kvkNummerField.setVisible(false);
            } else {
                emailField.setVisible(false);
            }
        });

        companyCheckBox.setOnAction(e -> {
            if (companyCheckBox.isSelected()) {
                privateCheckBox.setSelected(false);
                emailField.setVisible(false);
                bedrijfsnaamField.setVisible(true);
                kvkNummerField.setVisible(true);
            } else {
                bedrijfsnaamField.setVisible(false);
                kvkNummerField.setVisible(false);
            }
        });

        submitButton.setOnAction(e -> {
            naamField.clear();
            kentekenField.clear();
            emailField.clear();
            bedrijfsnaamField.clear();
            kvkNummerField.clear();
        });

        // Add all elements to the grid
        grid.getChildren().addAll(infoLabel, naamField, kentekenField, privateCheckBox, companyCheckBox, emailField, bedrijfsnaamField, kvkNummerField, submitButton);

        // Add the grid to the provided Pane
        p.getChildren().add(grid);
    }
}