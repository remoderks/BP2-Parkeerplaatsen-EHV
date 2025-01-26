package com.bp2parkeerplaatsenehv.Pages;

import com.bp2parkeerplaatsenehv.Model.ParticuliereKlant;
import com.bp2parkeerplaatsenehv.Model.ZakelijkeKlant;
import com.bp2parkeerplaatsenehv.controllers.Data.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InzageKlanten {
    private TableView<ParticuliereKlant> particuliereKlant;
    private ObservableList<ParticuliereKlant> particuliereKlanten = FXCollections.observableArrayList();
    private TableView<ZakelijkeKlant> zakelijkeKlant;
    private ObservableList<ZakelijkeKlant> zakelijkeKlanten = FXCollections.observableArrayList();
    private Button deleteButtonParticulier = new Button("Verwijderen");
    private Button updateButtonParticulier = new Button("Update");
    private VBox updateFormParticulier;
    private TextField naamFieldParticulier;
    private TextField kentekenFieldParticulier;
    private TextField emailFieldParticulier;
    private Button submitButtonUpdateFormParticulier;

    private Button deleteButtonZakelijk = new Button("Verwijderen");
    private Button updateButtonZakelijk = new Button("Update");
    private VBox updateFormZakelijk;
    private TextField naamFieldZakelijk;
    private TextField kentekenFieldZakelijk;
    private TextField kvkFieldZakelijk;
    private Button submitButtonUpdateFormZakelijk;

    public InzageKlanten(Pane klantenPane) {
        // Initialize ParticuliereKlant TableView
        particuliereKlant = new TableView<>();
        loadParticuliereKlanten();
        particuliereKlant.setItems(particuliereKlanten);
        TableColumn<ParticuliereKlant, String> naamColumnParticulier = new TableColumn<>("Naam");
        naamColumnParticulier.setCellValueFactory(new PropertyValueFactory<>("naam"));
        naamColumnParticulier.setPrefWidth(100);
        TableColumn<ParticuliereKlant, String> kentekenColumnParticulier = new TableColumn<>("Kenteken");
        kentekenColumnParticulier.setCellValueFactory(new PropertyValueFactory<>("kenteken"));
        kentekenColumnParticulier.setPrefWidth(100);
        TableColumn<ParticuliereKlant, String> emailColumnParticulier = new TableColumn<>("Email");
        emailColumnParticulier.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumnParticulier.setPrefWidth(100);
        particuliereKlant.getColumns().addAll(naamColumnParticulier, kentekenColumnParticulier, emailColumnParticulier);

        // Initialize ZakelijkeKlant TableView
        zakelijkeKlant = new TableView<>();
        loadZakelijkeKlanten();
        zakelijkeKlant.setItems(zakelijkeKlanten);
        TableColumn<ZakelijkeKlant, String> naamColumnZakelijk = new TableColumn<>("Naam");
        naamColumnZakelijk.setCellValueFactory(new PropertyValueFactory<>("naam"));
        naamColumnZakelijk.setPrefWidth(100);
        TableColumn<ZakelijkeKlant, String> kentekenColumnZakelijk = new TableColumn<>("Kenteken");
        kentekenColumnZakelijk.setCellValueFactory(new PropertyValueFactory<>("kenteken"));
        kentekenColumnZakelijk.setPrefWidth(100);
        TableColumn<ZakelijkeKlant, String> kvkColumnZakelijk = new TableColumn<>("kvknummer");
        kvkColumnZakelijk.setCellValueFactory(new PropertyValueFactory<>("kvkNumber"));
        kvkColumnZakelijk.setPrefWidth(100);
        zakelijkeKlant.getColumns().addAll(naamColumnZakelijk, kentekenColumnZakelijk, kvkColumnZakelijk);

        // Initialize ParticuliereKlant update form
        updateFormParticulier = new VBox(10);
        naamFieldParticulier = new TextField();
        kentekenFieldParticulier = new TextField();
        emailFieldParticulier = new TextField();
        submitButtonUpdateFormParticulier = new Button("Update klant");

        updateFormParticulier.getChildren().addAll(new Label("Naam"), naamFieldParticulier, new Label("Kenteken"), kentekenFieldParticulier, new Label("Email"), emailFieldParticulier, submitButtonUpdateFormParticulier);
        updateFormParticulier.setVisible(false);

        // Initialize ZakelijkeKlant update form
        updateFormZakelijk = new VBox(10);
        naamFieldZakelijk = new TextField();
        kentekenFieldZakelijk = new TextField();
        kvkFieldZakelijk = new TextField();
        submitButtonUpdateFormZakelijk = new Button("Update klant");

        updateFormZakelijk.getChildren().addAll(new Label("Naam"), naamFieldZakelijk, new Label("Kenteken"), kentekenFieldZakelijk, new Label("kvknummer"), kvkFieldZakelijk, submitButtonUpdateFormZakelijk);
        updateFormZakelijk.setVisible(false);

        // Event handlers for buttons
        updateButtonParticulier.setOnAction(event -> showUpdateFormParticulier());
        submitButtonUpdateFormParticulier.setOnAction(event -> updateParticuliereKlant());
        deleteButtonParticulier.setOnAction(event -> deleteParticuliereKlant());

        updateButtonZakelijk.setOnAction(event -> showUpdateFormZakelijk());
        submitButtonUpdateFormZakelijk.setOnAction(event -> updateZakelijkeKlant());
        deleteButtonZakelijk.setOnAction(event -> deleteZakelijkeKlant());

        // Create a HBox to hold the Vboxes with the tableviews and buttons, forms
        klantenPane.getChildren().clear();
        HBox hbox = new HBox(10);
        VBox vboxParticulier = new VBox(10);
        vboxParticulier.getChildren().addAll(particuliereKlant, updateButtonParticulier, deleteButtonParticulier, updateFormParticulier);
        VBox vboxZakelijk = new VBox(10);
        vboxZakelijk.getChildren().addAll(zakelijkeKlant, updateButtonZakelijk, deleteButtonZakelijk, updateFormZakelijk);
        hbox.getChildren().addAll(vboxParticulier, vboxZakelijk);

        // Add the hbox to the Pane
        klantenPane.getChildren().add(hbox);
    }

    private void loadParticuliereKlanten() {
        particuliereKlanten.clear();
        try {
            Connection connection = DatabaseHandler.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT naam, kenteken, email FROM ParticuliereKlanten";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String naam = resultSet.getString("naam");
                String kenteken = resultSet.getString("kenteken");
                String email = resultSet.getString("email");
                ParticuliereKlant klant = new ParticuliereKlant(naam, kenteken, email);
                particuliereKlanten.add(klant);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Er is een fout opgetreden bij het laden van particuliere klanten: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // ---------------   Load zakelijke klanten from the database ---------------------------
    private void loadZakelijkeKlanten() {
        zakelijkeKlanten.clear();
        try {
            Connection connection = DatabaseHandler.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT naam, kenteken, kvknummer FROM ZakelijkeKlanten";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String naam = resultSet.getString("naam");
                String kenteken = resultSet.getString("kenteken");
                int kvkNumber = resultSet.getInt("kvknummer");
                ZakelijkeKlant klant = new ZakelijkeKlant(naam, kenteken, kvkNumber);
                zakelijkeKlanten.add(klant);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            showAlert("Er is een fout opgetreden bij het laden van zakelijke klanten: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // ---------------   Show the update form for particuliere klanten ---------------------------
    private void showUpdateFormParticulier() {
        ParticuliereKlant selectedKlant = particuliereKlant.getSelectionModel().getSelectedItem();
        if (selectedKlant == null) {
            showAlert("Selecteer een particuliere klant om te updaten.");
            return;
        } else {
            naamFieldParticulier.setText(selectedKlant.getNaam());
            kentekenFieldParticulier.setText(selectedKlant.getKenteken());
            emailFieldParticulier.setText(selectedKlant.getEmail());
            updateFormParticulier.setVisible(true);
        }
    }

    // ---------------   Update the particuliere klant in the database ---------------------------
    private void updateParticuliereKlant() {
        ParticuliereKlant selectedKlant = particuliereKlant.getSelectionModel().getSelectedItem();
        if (selectedKlant == null) {
            showAlert("Selecteer een particuliere klant om te updaten.");
            return;
        }
        try {
            Connection connection = DatabaseHandler.getConnection();
            // update klantnamen table
            String insertKlantnaamQuery = "UPDATE Klantnamen SET naam = ? WHERE naam = ?";
            java.sql.PreparedStatement updateKlantnaamStmt = connection.prepareStatement(insertKlantnaamQuery);
            updateKlantnaamStmt.setString(1, naamFieldParticulier.getText());
            updateKlantnaamStmt.setString(2, selectedKlant.getNaam());
            updateKlantnaamStmt.executeUpdate();
            updateKlantnaamStmt.close();
            //update kentekens table
            String insertKentekenQuery = "UPDATE Kentekens SET kenteken = ? WHERE kenteken = ?";
            java.sql.PreparedStatement updateKentekenStmt = connection.prepareStatement(insertKentekenQuery);
            updateKentekenStmt.setString(1, kentekenFieldParticulier.getText());
            updateKentekenStmt.setString(2, selectedKlant.getKenteken());
            updateKentekenStmt.executeUpdate();
            updateKentekenStmt.close();
            //update particuliere klanten table
            String query = "UPDATE ParticuliereKlanten SET naam = ?, kenteken = ?, email = ? WHERE kenteken = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, naamFieldParticulier.getText());
            preparedStatement.setString(2, kentekenFieldParticulier.getText());
            preparedStatement.setString(3, emailFieldParticulier.getText());
            preparedStatement.setString(4, selectedKlant.getNaam());
            preparedStatement.setString(4, selectedKlant.getKenteken());
            preparedStatement.setString(4, selectedKlant.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            showAlert("Particuliere klant succesvol geüpdatet!");
            loadParticuliereKlanten();
        } catch (SQLException ex) {
            showAlert("Er is een fout opgetreden bij het updaten van de particuliere klant: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    // ---------------   Delete particuliere klant from the database ---------------------------
    private void deleteParticuliereKlant() {
        ParticuliereKlant selectedKlant = particuliereKlant.getSelectionModel().getSelectedItem();
        if (selectedKlant == null) {
            showAlert("Selecteer een particuliere klant om te verwijderen.");
            return;
        }
        try {
            Connection connection = DatabaseHandler.getConnection();
            String query = "DELETE FROM ParticuliereKlanten WHERE naam =? AND kenteken = ? AND email = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, selectedKlant.getNaam());
            preparedStatement.setString(2, selectedKlant.getKenteken());
            preparedStatement.setString(3, selectedKlant.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            showAlert("Particuliere klant succesvol verwijderd!");
            loadParticuliereKlanten();
        } catch (SQLException ex) {
            showAlert("Er is een fout opgetreden bij het verwijderen van de particuliere klant: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // ---------------   Show the update form for zakelijke klanten ---------------------------
    private void showUpdateFormZakelijk() {
        ZakelijkeKlant selectedKlant = zakelijkeKlant.getSelectionModel().getSelectedItem();
        if (selectedKlant == null) {
            showAlert("Selecteer een zakelijke klant om te updaten.");
            return;
        } else {
            naamFieldZakelijk.setText(selectedKlant.getNaam());
            kentekenFieldZakelijk.setText(selectedKlant.getKenteken());
            kvkFieldZakelijk.setText(String.valueOf(selectedKlant.getKvkNumber()));
            updateFormZakelijk.setVisible(true);
        }
    }


    // ---------------   Update zakelijke klant in the database ---------------------------
    private void updateZakelijkeKlant() {
        ZakelijkeKlant selectedKlant = zakelijkeKlant.getSelectionModel().getSelectedItem();
        if (selectedKlant == null) {
            showAlert("Selecteer een zakelijke klant om te updaten.");
            return;
        }
        try {
            Connection connection = DatabaseHandler.getConnection();
            String insertKlantnaamQuery = "UPDATE Klantnamen SET naam = ? WHERE naam = ?";
            java.sql.PreparedStatement updateKlantnaamStmt = connection.prepareStatement(insertKlantnaamQuery);
            updateKlantnaamStmt.setString(1, naamFieldZakelijk.getText());
            updateKlantnaamStmt.setString(2, selectedKlant.getNaam());
            updateKlantnaamStmt.executeUpdate();
            updateKlantnaamStmt.close();
            //update kentekens table
            String insertKentekenQuery = "UPDATE Kentekens SET kenteken = ? WHERE kenteken = ?";
            java.sql.PreparedStatement updateKentekenStmt = connection.prepareStatement(insertKentekenQuery);
            updateKentekenStmt.setString(1, kentekenFieldZakelijk.getText());
            updateKentekenStmt.setString(2, selectedKlant.getKenteken());
            updateKentekenStmt.executeUpdate();
            updateKentekenStmt.close();
            String query = "UPDATE ZakelijkeKlanten SET naam = ?, kenteken = ?, kvknummer = ? WHERE naam = ? AND kenteken = ? AND kvknummer = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, naamFieldZakelijk.getText());
            preparedStatement.setString(2, kentekenFieldZakelijk.getText());
            preparedStatement.setString(3, kvkFieldZakelijk.getText());
            preparedStatement.setString(4, selectedKlant.getNaam());
            preparedStatement.setString(5, selectedKlant.getKenteken());
            preparedStatement.setInt(6, selectedKlant.getKvkNumber());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            showAlert("Zakelijke klant succesvol geüpdatet!");
            loadZakelijkeKlanten();
        } catch (SQLException ex) {
            showAlert("Er is een fout opgetreden bij het updaten van de zakelijke klant: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    // ---------------   Delete zakelijke klant from the database ---------------------------
    private void deleteZakelijkeKlant() {
        ZakelijkeKlant selectedKlant = zakelijkeKlant.getSelectionModel().getSelectedItem();
        if (selectedKlant == null) {
            showAlert("Selecteer een zakelijke klant om te verwijderen.");
            return;
        }
        try {
            Connection connection = DatabaseHandler.getConnection();
            String query = "DELETE FROM ZakelijkeKlanten WHERE naam = ? AND kenteken = ? AND kvknummer = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, selectedKlant.getNaam());
            preparedStatement.setString(2, selectedKlant.getKenteken());
            preparedStatement.setInt(3, selectedKlant.getKvkNumber());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            showAlert("Zakelijke klant succesvol verwijderd!");
            loadZakelijkeKlanten();
        } catch (SQLException ex) {
            showAlert("Er is een fout opgetreden bij het verwijderen van de zakelijke klant: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    // ---------------   Show an alert dialog with the provided (error)message ---------------------------
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}