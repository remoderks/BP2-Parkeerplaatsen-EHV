package com.bp2parkeerplaatsenehv.Pages;

import com.bp2parkeerplaatsenehv.Model.Reserveringsafspraken;
import com.bp2parkeerplaatsenehv.controllers.Data.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InzageReserveringen {
    private TableView<Reserveringsafspraken> reserveringsAfspraken;
    private ObservableList<Reserveringsafspraken> reserveringen = FXCollections.observableArrayList();
    private Button deleteButton = new Button("Verwijderen");
    private Button updateButton = new Button("Updaten");
    private VBox updateForm;
    private TextField kentekenField;
    private TextField objectIDField;
    private TextField tijdslotField;
    private DatePicker datumField;
    private Button submitButtonUpdateForm;

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

        // Initialize the update form
        updateForm = new VBox(10);
        kentekenField = new TextField();
        objectIDField = new TextField();
        tijdslotField = new TextField();
        datumField = new DatePicker();
        submitButtonUpdateForm = new Button("Update reservering");

        updateForm.getChildren().addAll(new Label("Kenteken"),
                kentekenField, new Label("ObjectID"),
                objectIDField, new Label("Tijdslot"),
                tijdslotField, new Label("Datum"),
                datumField, submitButtonUpdateForm);
        updateForm.setVisible(false);

        // event handler submit button / delete button
        updateButton.setOnAction(event -> showUpdateForm());
        submitButtonUpdateForm.setOnAction(event -> updateReserveringsafspraak());
        deleteButton.setOnAction(e -> deleteReserveringsafspraak());

        // Created VBox to hold the TableView and buttons
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(reserveringsAfspraken, updateButton, deleteButton, updateForm);

        // Add the vbox to the Pane
        reserveringenPane.getChildren().add(vbox);
    }

    // Load the reserveringen from the database
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
        } catch (SQLException ex) {
            if (ex.getMessage().contains("DuplicateKeyException")) {
                showAlert("Gegevens al bekend in het systeem.");
            } else {
                showAlert("Er is een fout opgetreden bij het aanmelden: " + ex.getMessage());
            }
            ex.printStackTrace();
        }
    }

    private void showUpdateForm() {
        Reserveringsafspraken selectedReserveringsafspraak = reserveringsAfspraken.getSelectionModel().getSelectedItem();
        if (selectedReserveringsafspraak == null) {
            showAlert("Selecteer een reserveringsafspraak om te updaten.");
            return;
        } else {
            kentekenField.setText(selectedReserveringsafspraak.getKenteken());
            objectIDField.setText(selectedReserveringsafspraak.getObjectID());
            tijdslotField.setText(selectedReserveringsafspraak.getTijdslot());
            datumField.setValue(java.time.LocalDate.parse(selectedReserveringsafspraak.getDatum()));
            updateForm.setVisible(true);
        }
    }

    private void updateReserveringsafspraak() {
        // Get the selected reserveringsafspraak
        Reserveringsafspraken selectedReserveringsafspraak = reserveringsAfspraken.getSelectionModel().getSelectedItem();
        if (selectedReserveringsafspraak == null) {
            showAlert("Selecteer een reserveringsafspraak om te updaten.");
            return;
        }
        // Update the reserveringsafspraak in the database
        try {
            Connection connection = DatabaseHandler.getConnection();
            String query = "UPDATE Reserveringsafspraken SET kenteken = ?, objectID = ?, tijdslot = ?, datum = ? WHERE kenteken = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, kentekenField.getText());
            preparedStatement.setString(2, objectIDField.getText());
            preparedStatement.setString(3, tijdslotField.getText());
            preparedStatement.setString(4, datumField.getValue().toString());
            preparedStatement.setString(5, selectedReserveringsafspraak.getKenteken());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            showAlert("Reserveringsafspraak succesvol geüpdatet!");
            loadReserveringen();
        } catch (SQLException ex) {
            showAlert("Er is een fout opgetreden bij het updaten van de reserveringsafspraak: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void deleteReserveringsafspraak() {
        // Get the selected reserveringsafspraak
        Reserveringsafspraken selectedReserveringsafspraak = reserveringsAfspraken.getSelectionModel().getSelectedItem();
        if (selectedReserveringsafspraak == null) {
            showAlert("Selecteer een reserveringsafspraak om te verwijderen.");
            return;
        }
        // Delete the reserveringsafspraak from the database
        try {
            Connection connection = DatabaseHandler.getConnection();
            String query = "DELETE FROM Reserveringsafspraken WHERE kenteken = ? AND objectID = ? AND tijdslot = ? AND datum = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, selectedReserveringsafspraak.getKenteken());
            preparedStatement.setString(2, selectedReserveringsafspraak.getObjectID());
            preparedStatement.setString(3, selectedReserveringsafspraak.getTijdslot());
            preparedStatement.setString(4, selectedReserveringsafspraak.getDatum());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            showAlert("Reserveringsafspraak succesvol verwijderd!");
            loadReserveringen();
        } catch (SQLException ex) {
            showAlert("Er is een fout opgetreden bij het verwijderen van de reserveringsafspraak: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

