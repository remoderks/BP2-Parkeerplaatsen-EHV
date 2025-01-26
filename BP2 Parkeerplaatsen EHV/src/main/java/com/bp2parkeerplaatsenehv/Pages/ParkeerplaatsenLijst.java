package com.bp2parkeerplaatsenehv.Pages;

import com.bp2parkeerplaatsenehv.Builders.JsonBuilder;
import com.bp2parkeerplaatsenehv.Builders.ParkingObjectFilter;
import com.bp2parkeerplaatsenehv.Builders.MapHandler;
import com.bp2parkeerplaatsenehv.Model.ParkingData;
import com.bp2parkeerplaatsenehv.Model.ParkingObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ParkeerplaatsenLijst {
    private TableView<ParkingObject> parkeerplaatsenLijst = new TableView<>();
    private ObservableList<ParkingObject> parkeerplaatsen = FXCollections.observableArrayList();
    private MapHandler mapHandler;

    public ParkeerplaatsenLijst(Pane parkeerplaatsenPane) {
        // Initialize MapHandler
        mapHandler = new MapHandler();

        // Load the parkeerplaatsen
        loadParkeerplaatsen();
        // Set the data in the TableView
        parkeerplaatsenLijst.setItems(parkeerplaatsen);

        // Create the columns for the TableView
        TableColumn<ParkingObject, Integer> idColumn = new TableColumn<>("Parkeervak ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("objectId"));
        idColumn.setPrefWidth(70);

        TableColumn<ParkingObject, String> straatColumn = new TableColumn<>("Straatnaam");
        straatColumn.setCellValueFactory(new PropertyValueFactory<>("straat"));
        straatColumn.setSortType(TableColumn.SortType.ASCENDING);
        straatColumn.setPrefWidth(150);

        TableColumn<ParkingObject, String> typEnMerkColumn = new TableColumn<>("Type en Merk");
        typEnMerkColumn.setCellValueFactory(new PropertyValueFactory<>("typeEnMerk"));
        typEnMerkColumn.setPrefWidth(150);

        TableColumn<ParkingObject, Double> longitudeColumn = new TableColumn<>("Longitude");
        longitudeColumn.setCellValueFactory(cellData -> {
            ParkingObject parkingObject = cellData.getValue();
            return new SimpleObjectProperty<>(parkingObject.getGeoPoint().getLongitude());
        });
        longitudeColumn.setPrefWidth(100);

        TableColumn<ParkingObject, Double> latitudeColumn = new TableColumn<>("Latitude");
        latitudeColumn.setCellValueFactory(cellData -> {
            ParkingObject parkingObject = cellData.getValue();
            return new SimpleObjectProperty<>(parkingObject.getGeoPoint().getLatitude());
        });
        latitudeColumn.setPrefWidth(100);

        parkeerplaatsenLijst.getColumns().addAll(straatColumn, idColumn, typEnMerkColumn, longitudeColumn, latitudeColumn);
        parkeerplaatsenLijst.getSortOrder().add(straatColumn);
        // Disable the addition of an extra column
        parkeerplaatsenLijst.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Add TableView and Map to the layout
        HBox layout = new HBox(100, parkeerplaatsenLijst, mapHandler.getWebView());
        layout.setPadding(new Insets(10, 10, 10, 10));
        parkeerplaatsenPane.getChildren().add(layout);

        parkeerplaatsenLijst.setPrefWidth(500);
        parkeerplaatsenLijst.setPrefHeight(500);

        // Add listener for row selection
        parkeerplaatsenLijst.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mapHandler.clearMarkers();
                mapHandler.addPin(newValue.getGeoPoint().getLongitude(), newValue.getGeoPoint().getLatitude(), newValue.getStraat());
            }
        });
    }

    // loads the parkeerplaatsen from the Gemeente API and sets it to string value so it fits in the TableView.
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
                System.out.println("Number of parking objects: " + parkingObjects.size()); // Used to debug and check if any data is returned from the API.

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
