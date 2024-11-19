package com.bp2parkeerplaatsenehv.Menubar;

import com.bp2parkeerplaatsenehv.Pages.Reserveringsafspraken;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class ClickAbleMenuInzageReserveringen extends Menu {
    // Create a label in pane layout
    private Label label = new Label();
    private Pane reserveringen;
    // create a clickable label for the inzage reserveringen text in the menu.
    public ClickAbleMenuInzageReserveringen(String text){
        MenuItem dummy = new MenuItem();
        dummy.setVisible(false);
        label.setText(text);
        label.setOnMouseClicked(event -> {
            // Clears the screen.
            reserveringen.getChildren().clear();
            // opens the inzage reserveringen page.
            new Reserveringsafspraken(reserveringen);
        });
        setGraphic(label);
    }
    public void setPane(Pane p) {
        this.reserveringen = p;
    }
}
