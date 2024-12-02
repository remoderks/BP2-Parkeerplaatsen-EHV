package com.bp2parkeerplaatsenehv.Menubar;

import com.bp2parkeerplaatsenehv.Pages.ParkeerplaatsenLijst;
import com.bp2parkeerplaatsenehv.Pages.Reserveren;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class ClickAbleMenuParkeerplaatsenLijst extends Menu {
        private Label label = new Label();
        private Pane parkeerplaatsenlijst;

        public ClickAbleMenuParkeerplaatsenLijst(String text) {
            MenuItem dummy = new MenuItem();
            label.setText(text);
            label.setOnMouseClicked(event -> {
                // clear the screen
                parkeerplaatsenlijst.getChildren().clear();
                // opens the reserveren page.
                new ParkeerplaatsenLijst(parkeerplaatsenlijst);
            });
            setGraphic(label);
        }
        public void setPane(Pane p) {
            this.parkeerplaatsenlijst = p;
        }
}
