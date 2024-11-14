package com.bp2parkeerplaatsenehv.Pages;
import com.bp2parkeerplaatsenehv.Builders.JsonBuilder;
import com.bp2parkeerplaatsenehv.Builders.MapHandler;
import com.bp2parkeerplaatsenehv.Builders.ParkingObjectFilter;
import com.bp2parkeerplaatsenehv.Builders.UIFormBuilder;
import com.bp2parkeerplaatsenehv.Model.ParkingData;
import com.bp2parkeerplaatsenehv.Model.ParkingObject;
import com.bp2parkeerplaatsenehv.controllers.Data.DataReader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.List;

public class OverzichtParkeerplaatsen {
    private final List<ParkingObject> parkingObjects;
    private final MapHandler mapHandler;
    private final UIFormBuilder formBuilder;
    private final ParkingObjectFilter parkingObjectFilter;
    private final JsonBuilder jsonBuilder;

    public OverzichtParkeerplaatsen(Pane overzicht) {
        ParkingData data = new DataReader().readData();
        parkingObjects = data.getParkingObjects();

        mapHandler = new MapHandler();
        formBuilder = new UIFormBuilder(parkingObjects);
        parkingObjectFilter = new ParkingObjectFilter(parkingObjects);
        jsonBuilder = new JsonBuilder();

        VBox layout = new VBox(10, formBuilder.buildForm(), mapHandler.getWebView());
        overzicht.getChildren().add(layout);

        initializeEvents();
    }

    private void initializeEvents() {
        formBuilder.getSearchButton().setOnAction(event -> {
            mapHandler.clearMarkers();
            // get values from the form
            Integer objectId = formBuilder.getObjectIdComboBox().getValue();
            String straat = formBuilder.getStraatComboBox().getValue();
            String typeEnMerk = formBuilder.getTypeEnMerkComboBox().getValue();

            // Filter parking objects based on the selected values in the form/application
            List<ParkingObject> filteredParkingObjects = parkingObjectFilter.filter(objectId, straat, typeEnMerk);
            if (filteredParkingObjects.isEmpty()) {
                ShowError("Parkeerplaatsen niet gevonden", "Er zijn geen parkeerplaatsen gevonden met de opgegeven criteria.");
            } else {
                parkingObjectFilter.filter(objectId, straat, typeEnMerk)
                        .forEach(po -> mapHandler.addPin(po.getGeoPoint().getLongitude(), po.getGeoPoint().getLatitude(), po.getStraat()));
            }});
        formBuilder.getDeleteButton().setOnAction(event -> {
            mapHandler.clearMarkers();
            formBuilder.getObjectIdComboBox().setValue(null);
            formBuilder.getStraatComboBox().setValue(null);
            formBuilder.getTypeEnMerkComboBox().setValue(null);
        });
    }
    private void ShowError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Fout");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
