package app.ageofspice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AgeOfSpiceApp extends Application {

    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1010;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AgeOfSpiceApp.class.getResource("map.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setTitle("Age of Spice");
        //stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}