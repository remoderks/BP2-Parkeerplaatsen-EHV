package com.bp2parkeerplaatsenehv.Pages;

import com.bp2parkeerplaatsenehv.Model.ParkingData;
import com.bp2parkeerplaatsenehv.Model.ParkingObject;
import com.bp2parkeerplaatsenehv.controllers.Data.DataReader;
import com.bp2parkeerplaatsenehv.controllers.Data.DatabaseHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Reserveren {
    private ComboBox<String> kentekenComboBox;
    private ComboBox<String> objectIDComboBox;
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

        // Initialize TextField and ComboBoxes
        kentekenComboBox = new ComboBox<>();
        objectIDComboBox = new ComboBox<>();
        tijdslotComboBox = new ComboBox<>();
        datumPicker = new DatePicker();

        // Populate ComboBoxes with data from the database
        populateComboBox(kentekenComboBox, "SELECT kenteken FROM Kentekens");
        // Different method to populate objectIDComboBox -> populateObjectIDComboBox() (Users can choose from the available parking spots listed in API)
        populateObjectIDComboBox();
        populateComboBox(tijdslotComboBox, "SELECT tijdslot FROM BeschikbaarheidParkeerplaatsen");

        // Set prompts
        kentekenComboBox.setPromptText("Kenteken");
        objectIDComboBox.setPromptText("ObjectID(parkeervak)");
        tijdslotComboBox.setPromptText("Tijdslot");
        datumPicker.setPromptText("Datum");

        // Set constraints
        GridPane.setConstraints(kentekenComboBox, 0, 0);
        GridPane.setConstraints(objectIDComboBox, 0, 1);
        GridPane.setConstraints(tijdslotComboBox, 0, 2);
        GridPane.setConstraints(datumPicker, 0, 3);

        // Initialize buttons
        submitButton = new Button("Reserveren");
        resetButton = new Button("Reset");

        // Set constraints for buttons
        GridPane.setConstraints(submitButton, 0, 5);
        GridPane.setConstraints(resetButton, 0, 6);

        // Add event handlers for buttons
        submitButton.setOnAction(e -> handleSubmit());
        resetButton.setOnAction(e -> handleReset());

        // Add all elements to the grid
        grid.getChildren().addAll(kentekenComboBox, objectIDComboBox, tijdslotComboBox, datumPicker, submitButton, resetButton);

        // Add the grid to the provided Pane
        p.getChildren().add(grid);
    }

    private void populateComboBox(ComboBox<String> comboBox, String query) {
        List<String> items = new ArrayList<>();
        try (Connection conn = DatabaseHandler.getConnection();
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
    private void populateObjectIDComboBox() {
        DataReader dataReader = new DataReader();
        ParkingData parkingData = dataReader.readData();

        if (parkingData != null && !parkingData.getParkingObjects().isEmpty()) {
            List<String> objectIDs = new ArrayList<>();
            for (ParkingObject parkingObject : parkingData.getParkingObjects()) {
                objectIDs.add(parkingObject.getObjectId().toString());
            }
            objectIDComboBox.getItems().addAll(objectIDs);
        } else {
            System.out.println("No parking data found or records are empty.");
        }
    }

    private void handleSubmit() {
        // Handle submit logic here
        String objectID = objectIDComboBox.getValue();
        String kenteken = kentekenComboBox.getValue();
        String tijdslot = tijdslotComboBox.getValue();
        String datum = datumPicker.getValue().toString();

        // Add your database insert logic here
    }

    private void handleReset() {
        objectIDComboBox.setValue(null);
        kentekenComboBox.setValue(null);
        tijdslotComboBox.setValue(null);
        datumPicker.setValue(null);
    }
}