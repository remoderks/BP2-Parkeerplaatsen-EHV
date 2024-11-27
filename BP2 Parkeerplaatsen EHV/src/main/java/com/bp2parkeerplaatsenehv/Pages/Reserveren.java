package com.bp2parkeerplaatsenehv.Pages;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Reserveren {
    private ComboBox<String> naamComboBox;
    private ComboBox<String> kentekenComboBox;
    private ComboBox<String> tijdslotComboBox;
    private DatePicker datumPicker;
    private Button submitButton;
    private Button resetButton;

    public Reserveren(Pane p) {
        // Create the main layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Initialize ComboBoxes
        naamComboBox = new ComboBox<>();
        kentekenComboBox = new ComboBox<>();
        tijdslotComboBox = new ComboBox<>();
        datumPicker = new DatePicker();

        // Populate ComboBoxes with data from the database
        populateComboBox(naamComboBox, "SELECT naam FROM klanten");
        populateComboBox(kentekenComboBox, "SELECT kenteken FROM kentekens");
        tijdslotComboBox.getItems().addAll("SELECT tijdslot FROM reserveringen");

        // Set prompts
        naamComboBox.setPromptText("Naam");
        kentekenComboBox.setPromptText("Kenteken");
        tijdslotComboBox.setPromptText("Tijdslot");
        datumPicker.setPromptText("Datum");

        // Set constraints
        GridPane.setConstraints(naamComboBox, 0, 0);
        GridPane.setConstraints(kentekenComboBox, 0, 1);
        GridPane.setConstraints(tijdslotComboBox, 0, 2);
        GridPane.setConstraints(datumPicker, 0, 3);

        // Initialize buttons
        submitButton = new Button("Submit");
        resetButton = new Button("Reset");

        // Set constraints for buttons
        GridPane.setConstraints(submitButton, 0, 4);
        GridPane.setConstraints(resetButton, 1, 4);

        // Add event handlers for buttons
        submitButton.setOnAction(e -> handleSubmit());
        resetButton.setOnAction(e -> handleReset());

        // Add all elements to the grid
        grid.getChildren().addAll(naamComboBox, kentekenComboBox, tijdslotComboBox, datumPicker, submitButton, resetButton);

        // Add the grid to the provided Pane
        p.getChildren().add(grid);
    }

    private void populateComboBox(ComboBox<String> comboBox, String query) {
        List<String> items = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdatabase", "username", "password");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                items.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comboBox.getItems().addAll(items);
    }

    private void handleSubmit() {
        // Handle submit logic here
        String naam = naamComboBox.getValue();
        String kenteken = kentekenComboBox.getValue();
        String tijdslot = tijdslotComboBox.getValue();
        String datum = datumPicker.getValue().toString();

        // Add your database insert logic here
    }

    private void handleReset() {
        naamComboBox.setValue(null);
        kentekenComboBox.setValue(null);
        tijdslotComboBox.setValue(null);
        datumPicker.setValue(null);
    }
}