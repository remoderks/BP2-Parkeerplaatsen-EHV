package com.bp2parkeerplaatsenehv.Menubar;

import com.bp2parkeerplaatsenehv.Pages.Inschrijfpagina;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class ClickAbleMenuInschrijvingspagina extends Menu {
    private Label label = new Label();
    private Pane persoongegevens;

    public ClickAbleMenuInschrijvingspagina(String text) {
        MenuItem dummy = new MenuItem();
        label.setText(text);
        label.setOnMouseClicked(event -> {
            // clear the screen
            persoongegevens.getChildren().clear();
            // opens the persoongegevens page.
            new Inschrijfpagina(persoongegevens);
        });
        setGraphic(label);
    }
    public void setPane(Pane p) {
        this.persoongegevens = p;
    }
}
