package com.bp2parkeerplaatsenehv.Pages;

import com.bp2parkeerplaatsenehv.Model.Reserveringsafspraken;
import com.bp2parkeerplaatsenehv.controllers.Data.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class InzageReserveringen {
    private TableView<Reserveringsafspraken> reserveringsAfspraken;
    private ObservableList<Reserveringsafspraken> reserveringen = FXCollections.observableArrayList();
    private Button deleteButton = new Button("Verwijderen");
    private Button updateButton = new Button("Updaten");

    public InzageReserveringen(Pane reserveringenPane) {
        // Initialize the TableView
        reserveringsAfspraken = new TableView<>();
        // Load the reserveringen
        loadReserveringen();
        // Set the data in the TableView
        reserveringsAfspraken.setItems(reserveringen);
        // Create the columns for the TableView
        TableColumn<Reserveringsafspraken, String> kentekenColumn = new TableColumn<>("Kenteken");
        kentekenColumn.setCellValueFactory(new PropertyValueFactory<>("kenteken"));
        kentekenColumn.setPrefWidth(100);

        TableColumn<Reserveringsafspraken, String> objectIDColumn = new TableColumn<>("Parkeervak (objectID)");
        objectIDColumn.setCellValueFactory(new PropertyValueFactory<>("objectID"));
        objectIDColumn.setPrefWidth(100);

        TableColumn<Reserveringsafspraken, String> tijdslotColumn = new TableColumn<>("Tijdslot");
        tijdslotColumn.setCellValueFactory(new PropertyValueFactory<>("tijdslot"));
        tijdslotColumn.setPrefWidth(100);

        TableColumn<Reserveringsafspraken, String> datumColumn = new TableColumn<>("Datum");
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        datumColumn.setPrefWidth(100);

        // Add the columns to the TableView
        reserveringsAfspraken.getColumns().addAll(kentekenColumn, objectIDColumn, tijdslotColumn, datumColumn);

        // Create a vBox to hold the TableView and buttons
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(reserveringsAfspraken, updateButton, deleteButton);

        // Add the vbox to the Pane
        reserveringenPane.getChildren().add(vbox);
    }

    private void loadReserveringen() {
        reserveringen.clear();
        try {
            // Establish a database connection
            Connection connection = DatabaseHandler.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT kenteken, objectID, tijdslot, datum FROM Reserveringsafspraken";
            ResultSet resultSet = statement.executeQuery(query);

            // Iterate through the result set and add data to the ObservableList
            while (resultSet.next()) {
                String kenteken = resultSet.getString("kenteken");
                String objectID = resultSet.getString("objectID");
                String tijdslot = resultSet.getString("tijdslot");
                String datum = resultSet.getString("datum");

                Reserveringsafspraken reserveringsafspraken = new Reserveringsafspraken(kenteken, objectID, tijdslot, datum);
                reserveringen.add(reserveringsafspraken);
            }
            // Close the connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("An error occurred while loading reserveringen: " + e.getMessage());
            e.printStackTrace();
        }
    }
}