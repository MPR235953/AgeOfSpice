package app.ageofspice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
        MapController.board[3][3].setTileType(TileType.PEOPLE_DRED_SHIP);

        MapController.board[5][5].setTileType(TileType.EMPTY_SPACE);
    }

    public static void main(String[] args) {
        launch();
    }
}