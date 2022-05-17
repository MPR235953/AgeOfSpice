package app.ageofspice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;


public class AgeOfSpiceApp extends Application {

    static Rectangle2D screenBounds = Screen.getPrimary().getBounds();

    public static final int SCREEN_WIDTH = (int)screenBounds.getWidth();
    public static final int SCREEN_HEIGHT = (int)screenBounds.getHeight() - 70;     // -70px bo pasek ma 70px
    public static final int FRAME_SIZE = 100;                                       // wpisac ile px bedzie mial banner

    public static Stage stage = null;

    @Override
    public void start(Stage stage) throws IOException {

        AgeOfSpiceApp.stage = stage;
        //SceneController.switchToFXML("start.fxml");
        //SceneController.switchToFXML("speciesChoice.fxml");
        SceneController.switchToFXML("map.fxml");

    }

    public static void main(String[] args) {
        launch();
    }
}