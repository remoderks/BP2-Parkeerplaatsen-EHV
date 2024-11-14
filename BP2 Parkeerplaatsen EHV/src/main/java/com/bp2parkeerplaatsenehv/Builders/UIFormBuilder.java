package com.bp2parkeerplaatsenehv.Builders;
import com.bp2parkeerplaatsenehv.Model.ParkingObject;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import java.util.List;
import java.util.stream.Collectors;

public class UIFormBuilder {
    private ComboBox<Integer> objectIdComboBox;
    private ComboBox<String> straatComboBox;
    private ComboBox<String> typeEnMerkComboBox;
    private final Button searchButton = new Button("Zoek parkeerplaats(en)");
    private final Button deleteButton = new Button("Verwijder opdracht");
    private final Button clearID = new Button("X");
    private final Button clearStraat = new Button("X");
    private final Button clearTypeEnMerk = new Button("X");
    private final List<ParkingObject> parkingObjects;

    public UIFormBuilder(List<ParkingObject> parkingObjects) {
        this.parkingObjects = parkingObjects;
        clearID.setOnAction(event -> objectIdComboBox.setValue(null));
        clearStraat.setOnAction(event -> straatComboBox.setValue(null));
        clearTypeEnMerk.setOnAction(event -> typeEnMerkComboBox.setValue(null));
    }

    public GridPane buildForm() {
        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setVgap(10);
        form.setHgap(10);

        Label objectIdLabel = new Label("Object ID:");
        objectIdComboBox = createComboBox(parkingObjects.stream()
                .map(ParkingObject::getObjectId)
                .distinct()
                .collect(Collectors.toList()));

        Label straatLabel = new Label("Straat:");
        straatComboBox = createComboBox(parkingObjects.stream()
                .map(ParkingObject::getStraat)
                .distinct()
                .collect(Collectors.toList()));

        Label typeEnMerkLabel = new Label("Type en Merk:");
        typeEnMerkComboBox = createComboBox(parkingObjects.stream()
                .map(ParkingObject::getTypeEnMerk)
                .distinct()
                .collect(Collectors.toList()));

        form.add(objectIdLabel, 0, 0);
        form.add(objectIdComboBox, 1, 0);
        form.add(clearID, 2, 0);
        form.add(straatLabel, 0, 1);
        form.add(straatComboBox, 1, 1);
        form.add(clearStraat, 2, 1);
        form.add(typeEnMerkLabel, 0, 2);
        form.add(typeEnMerkComboBox, 1, 2);
        form.add(clearTypeEnMerk, 2, 2);
        form.add(searchButton, 1, 3);
        form.add(deleteButton, 1, 4);

        return form;
    }

    private <T> ComboBox<T> createComboBox(List<T> items) {
        ComboBox<T> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(items);
        return comboBox;
    }

    public ComboBox<Integer> getObjectIdComboBox() { return objectIdComboBox; }
    public ComboBox<String> getStraatComboBox() { return straatComboBox; }
    public ComboBox<String> getTypeEnMerkComboBox() { return typeEnMerkComboBox; }
    public Button getSearchButton() { return searchButton; }
    public Button getDeleteButton() { return deleteButton; }
}
