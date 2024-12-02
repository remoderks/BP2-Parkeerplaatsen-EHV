package com.bp2parkeerplaatsenehv.Pages;

import com.bp2parkeerplaatsenehv.Builders.JsonBuilder;
import com.bp2parkeerplaatsenehv.Builders.ParkingObjectFilter;
import com.bp2parkeerplaatsenehv.Model.ParkingData;
import com.bp2parkeerplaatsenehv.Model.ParkingObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ParkeerplaatsenLijst {
    private TableView<ParkingObject> parkeerplaatsenLijst = new TableView<>();
    private ObservableList<ParkingObject> parkeerplaatsen = FXCollections.observableArrayList();

    public ParkeerplaatsenLijst(Pane parkeerplaatsenPane) {
        // Load the parkeerplaatsen
        loadParkeerplaatsen();
        // Set the data in the TableView
        parkeerplaatsenLijst.setItems(parkeerplaatsen);

        // Create the columns for the TableView
        TableColumn<ParkingObject, Integer> idColumn = new TableColumn<>("Parkeervak ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("objectId"));

        TableColumn<ParkingObject, String> straatColumn = new TableColumn<>("Straatnaam");
        straatColumn.setCellValueFactory(new PropertyValueFactory<>("straat"));

        TableColumn<ParkingObject, String> typEnMerkColumn = new TableColumn<>("Type en Merk");
        typEnMerkColumn.setCellValueFactory(new PropertyValueFactory<>("typeEnMerk"));

        parkeerplaatsenLijst.getColumns().addAll(idColumn, straatColumn, typEnMerkColumn);
        parkeerplaatsenPane.getChildren().add(parkeerplaatsenLijst);

        parkeerplaatsenLijst.setPrefWidth(750);
        parkeerplaatsenLijst.setPrefHeight(500);
    }

    private void loadParkeerplaatsen() {
        parkeerplaatsen.clear();
        try {
            // Fetch the API data
            String apiUrl = "https://data.eindhoven.nl/api/explore/v2.1/catalog/datasets/parkeerplaatsen/records?limit=100";
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // Set timeout if needed
            connection.setReadTimeout(5000);

            // Read the response
            try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
                // Use JsonBuilder to parse the JSON response into ParkingData object
                JsonBuilder jsonBuilder = new JsonBuilder();
                ParkingData parkingData = jsonBuilder.gson.fromJson(reader, ParkingData.class);
                List<ParkingObject> parkingObjects = parkingData.getParkingObjects();
                System.out.println("Number of parking objects: " + parkingObjects.size());

                // Filter if needed, using ParkingObjectFilter (optional)
                ParkingObjectFilter filter = new ParkingObjectFilter(parkingObjects);
                List<ParkingObject> filteredParkingObjects = filter.filter(null, null, null); // Adjust filtering parameters as needed

                // Add the filtered list to ObservableList
                parkeerplaatsen.addAll(filteredParkingObjects);
            }

        } catch (Exception e) {
            System.out.println("An error occurred while loading parking objects: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
