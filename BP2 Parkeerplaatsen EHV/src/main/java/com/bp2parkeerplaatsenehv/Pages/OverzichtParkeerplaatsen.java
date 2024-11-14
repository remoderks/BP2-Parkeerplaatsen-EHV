package com.bp2parkeerplaatsenehv.Pages;

import com.bp2parkeerplaatsenehv.Model.ParkingData;
import com.bp2parkeerplaatsenehv.controllers.Data.DataReader;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class OverzichtParkeerplaatsen {

    public OverzichtParkeerplaatsen(Pane overzicht) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Load the map.html file from the resources directory
        webEngine.load(getClass().getResource("/com/bp2parkeerplaatsenehv/map.html").toExternalForm());

        // Fetch the JSON data using DataReader
        ParkingData data = new DataReader().readData();
        System.out.println(data.getParkingObjects().get(0).ObjectId);

//        // Pass the JSON data to the WebView
//        webEngine.setOnAlert(event -> {
//            webEngine.executeScript("processJsonData(" + jsonData + ")");
//        });

        overzicht.getChildren().add(webView);
    }
}