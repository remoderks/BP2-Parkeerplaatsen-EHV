package com.bp2parkeerplaatsenehv.Menubar;

import com.bp2parkeerplaatsenehv.Pages.OverzichtParkeerplaatsen;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class ClickAbleMenuOverzichtParkeerplaatsen extends Menu {
    private Label label = new Label();
    private Pane overzicht;

    public ClickAbleMenuOverzichtParkeerplaatsen(String text) {
        MenuItem dummy = new MenuItem();
        label.setText(text);
        label.setOnMouseClicked(event -> {
            // clear the screen
            overzicht.getChildren().clear();
            // opens the overzicht parkeerplaatsen page.
            new OverzichtParkeerplaatsen(overzicht);
        });
        setGraphic(label);
    }
    public void setPane(Pane p) {
        this.overzicht = p;
    }
}
