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
        //System.out.println(screenBounds);

    public static final int SCREEN_WIDTH = (int)screenBounds.getWidth();
    public static final int SCREEN_HEIGHT = (int)screenBounds.getHeight() - 70;     // -70px bo pasek ma 70px
    public static final int FRAME_SIZE = 100;                                       // wpisac ile px bedzie mial banner

    @Override
    public void start(Stage stage) throws IOException {

        /*SceneController sceneController = new SceneController(stage);
        sceneController.makeScene("speciesChoice.fxml");*/

        //FXMLLoader fxmlLoader = new FXMLLoader(AgeOfSpiceApp.class.getResource("speciesChoice.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(AgeOfSpiceApp.class.getResource("map.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
        //scene.getStylesheets().add(String.valueOf(getClass().getResource("style.css")));
        stage.setTitle("Age of Spice");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}