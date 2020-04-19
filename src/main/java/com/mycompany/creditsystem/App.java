package com.mycompany.creditsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.StageStyle;

public class App extends Application {

    public static Scene scene;
    public static Stage stage;
    public static double xOffset = 0;
    public static double yOffset = 0;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1280, 720);
        App.stage = stage;

        // Makes the background transparent
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

        // Sets the logo for the application
        stage.getIcons().add(new Image(App.class.getResourceAsStream("images/icon.png")));

        // Makes the navigation bar transparent
        stage.initStyle(StageStyle.TRANSPARENT);

        // sets the title of the application
        stage.setTitle("CREDIT");

        stage.setScene(scene);
        stage.show();

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void load(String[] args) {
        launch();
    }
}
