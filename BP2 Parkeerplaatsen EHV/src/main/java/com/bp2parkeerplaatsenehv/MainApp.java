package com.bp2parkeerplaatsenehv;
import com.bp2parkeerplaatsenehv.Menubar.MenuBar;
import com.bp2parkeerplaatsenehv.Pages.Homepage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        //create a menubar
        Pane root = new Pane();
        MenuBar menuBar = new MenuBar(root);
        // setup homepage as initial page
        new Homepage(root);
        // setup scene and show the stage in a VBox
        VBox vBox = new VBox(menuBar, root);

        Scene scene = new Scene(vBox, 1600, 1000);
        stage.setScene(scene);
        stage.setTitle("Openbare parkeerplaatsen EHV");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}