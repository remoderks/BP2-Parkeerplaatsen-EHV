package com.bp2parkeerplaatsenehv.Menubar;

import com.bp2parkeerplaatsenehv.Pages.InvoerenParkeerplaatsen;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class ClickAbleMenuBeschikbaarheidParkeerplaats extends Menu{
    private Label label = new Label();
    private Pane beschikbaarheid;

    public ClickAbleMenuBeschikbaarheidParkeerplaats(String text) {
        MenuItem dummy = new MenuItem();
        label.setText(text);
        label.setOnMouseClicked(event -> {
            // clear the screen
            beschikbaarheid.getChildren().clear();
            // opens the beschikbaarheid page.
            new InvoerenParkeerplaatsen(beschikbaarheid);
        });
        setGraphic(label);
    }
    public void setPane(Pane p) {
        this.beschikbaarheid = p;
    }
}