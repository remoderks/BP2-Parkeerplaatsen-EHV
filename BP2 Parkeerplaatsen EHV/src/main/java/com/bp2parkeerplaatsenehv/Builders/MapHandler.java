package com.bp2parkeerplaatsenehv.Builders;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class MapHandler {
    private final WebEngine webEngine;
    private final WebView webView;

    public MapHandler() {
        webView = new WebView();
        webEngine = webView.getEngine();
        webView.setPrefSize(800, 600);
        loadMap();
    }

    private void loadMap() {
        String mapUrl = getClass().getResource("/com/bp2parkeerplaatsenehv/map.html").toExternalForm();
        webEngine.load(mapUrl);
    }

    public WebView getWebView() {
        return webView;
    }

    public void addPin(double longitude, double latitude, String straat) {
        String script = String.format("addPin(%f, %f, '%s');", longitude, latitude, straat);
        webEngine.executeScript(script);
    }

    public void clearMarkers() {
        webEngine.executeScript("clearMarkers();");
    }
}
