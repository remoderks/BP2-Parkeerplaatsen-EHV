package com.bp2parkeerplaatsenehv.Pages;

import com.bp2parkeerplaatsenehv.Model.ParkingData;
import com.bp2parkeerplaatsenehv.Model.ParkingObject;
import com.bp2parkeerplaatsenehv.controllers.Data.DataReader;
import com.bp2parkeerplaatsenehv.controllers.Data.DatabaseHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.sql.*;
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
        Label infoLabel = new Label("Maak hieronder een reservering!");
        grid.setVgap(8);
        grid.setHgap(10);

        // Initialize TextField and ComboBoxes
        kentekenComboBox = new ComboBox<>();
        objectIDComboBox = new ComboBox<>();
        tijdslotComboBox = new ComboBox<>();
        datumPicker = new DatePicker();
        // Set preferred width for ComboBoxes and DatePicker
        double prefWidth = 200;
        kentekenComboBox.setPrefWidth(prefWidth);
        objectIDComboBox.setPrefWidth(prefWidth);
        tijdslotComboBox.setPrefWidth(prefWidth);
        datumPicker.setPrefWidth(prefWidth);
        // Set constraints
        GridPane.setConstraints(kentekenComboBox, 0, 1);
        GridPane.setConstraints(objectIDComboBox, 0, 2);
        GridPane.setConstraints(tijdslotComboBox, 0, 3);
        GridPane.setConstraints(datumPicker, 0, 4);
        // Set prompts
        kentekenComboBox.setPromptText("Kenteken");
        objectIDComboBox.setPromptText("ObjectID(parkeervak)");
        tijdslotComboBox.setPromptText("Tijdslot");
        datumPicker.setPromptText("Datum");
        // Populate ComboBoxes with data from the database
        populateComboBox(kentekenComboBox, "SELECT kenteken FROM Kentekens");
        // Different method to populate objectIDComboBox -> populateObjectIDComboBox() (Users can choose from the available parking spots listed in API)
        populateObjectIDComboBox();
        populateComboBox(tijdslotComboBox, "SELECT tijdslot FROM BeschikbaarheidParkeerplaatsen");


        // Initialize buttons
        submitButton = new Button("Reserveren");
        resetButton = new Button("Reset");
        // Set constraints for buttons, width and add event handlers
        GridPane.setConstraints(submitButton, 0, 6);
        GridPane.setConstraints(resetButton, 0, 7);
        submitButton.setPrefWidth(prefWidth);
        resetButton.setPrefWidth(prefWidth);
        submitButton.setOnAction(e -> handleSubmitButton());
        resetButton.setOnAction(e -> handleReset());

        // Add all elements to the grid
        grid.getChildren().addAll(infoLabel, kentekenComboBox, objectIDComboBox, tijdslotComboBox, datumPicker, submitButton, resetButton);

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

    private void handleSubmitButton() {
        // Validate if fields are not empty
        if (objectIDComboBox.getValue() == null || kentekenComboBox.getValue() == null ||
                tijdslotComboBox.getValue() == null || datumPicker.getValue() == null) {
            showAlert("Alle velden moeten worden ingevuld.");
            return;
        }

        String objectID = objectIDComboBox.getValue();
        String kenteken = kentekenComboBox.getValue();
        String tijdslot = tijdslotComboBox.getValue();
        String datum = datumPicker.getValue().toString();

        // Database insert logic
        try (Connection connection = DatabaseHandler.getConnection()) {
            String query = "INSERT INTO Reserveringsafspraken (objectID, kenteken, tijdslot, datum) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, objectID);
            preparedStatement.setString(2, kenteken);
            preparedStatement.setString(3, tijdslot);
            preparedStatement.setString(4, datum);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            showAlert("Reservering succesvol aangemaakt!");
        } catch (SQLException ex) {
            if (ex.getMessage().contains("DuplicateKeyException")) {
                showAlert("Reservering bestaat al.");
            } else {
                showAlert("Er is een fout opgetreden bij het maken van de reservering: " + ex.getMessage());
            }
            ex.printStackTrace();
        }
    }

    private void handleReset() {
        objectIDComboBox.setValue(null);
        kentekenComboBox.setValue(null);
        tijdslotComboBox.setValue(null);
        datumPicker.setValue(null);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Melding");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}