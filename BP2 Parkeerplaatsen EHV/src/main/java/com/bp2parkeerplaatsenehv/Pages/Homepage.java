package com.bp2parkeerplaatsenehv.Pages;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Homepage {
    public Homepage(Pane p) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        Label label = new Label("Welkom bij de reserveringsapp van parkeerplaatsen van EHV");
        ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/com/bp2parkeerplaatsenehv/Logo Eindhoven.png")));
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label, logo);
        p.getChildren().add(vbox);

    }
}
