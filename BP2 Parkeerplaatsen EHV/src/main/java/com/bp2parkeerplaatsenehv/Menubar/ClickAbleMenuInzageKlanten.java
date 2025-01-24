package com.bp2parkeerplaatsenehv.Menubar;
import com.bp2parkeerplaatsenehv.Pages.InzageKlanten;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class ClickAbleMenuInzageKlanten extends Menu{
    private Label label = new Label();
    private Pane inzageKlanten;

    public ClickAbleMenuInzageKlanten(String text) {
        MenuItem dummy = new MenuItem();
        label.setText(text);
        label.setOnMouseClicked(event -> {
            // clear the screen
            inzageKlanten.getChildren().clear();
            // opens the inzageKlanten page.
            new InzageKlanten(inzageKlanten);
        });
        setGraphic(label);
    }
    public void setPane(Pane p) {
        this.inzageKlanten = p;
    }
}