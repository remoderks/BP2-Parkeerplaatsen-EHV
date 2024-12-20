package com.bp2parkeerplaatsenehv.Menubar;

import com.bp2parkeerplaatsenehv.Pages.InzageReserveringen;
import com.bp2parkeerplaatsenehv.Pages.Reserveren;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class ClickAbleMenuReserveren extends Menu {
    private Label label = new Label();
    private Pane reserveren;

    public ClickAbleMenuReserveren(String text) {
        MenuItem dummy = new MenuItem();
        label.setText(text);
        label.setOnMouseClicked(event -> {
            // clear the screen
            reserveren.getChildren().clear();
            // opens the reserveren page.
            new Reserveren(reserveren);
        });
        setGraphic(label);
    }
    public void setPane(Pane p) {
        this.reserveren = p;
    }
}
