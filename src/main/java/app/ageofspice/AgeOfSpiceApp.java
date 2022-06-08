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

    public static SoundTrack soundTrack = new SoundTrack();

    @Override
    public void start(Stage stage) throws IOException {
        soundTrack.init("src/main/resources/app/ageofspice/music");
        soundTrack.playMedia();

        AgeOfSpiceApp.stage = stage;
        //SceneController.switchToFXML("start.fxml");
        //SceneController.switchToFXML("speciesChoice.fxml");

        SceneController.switchToFXML("map.fxml");
        //SceneController.switchToFXML("playerFrame.fxml");
        //SceneController.switchToFXML("end.fxml");

        AgeOfSpiceApp.stage.setOnCloseRequest(event -> end());
    }

    public static void end(){
        // podczas zamykania aplikacji trzeba zakonczyc jeszcze dzialanie timera ktory liczy czas do konca tury gracza
        // inaczej po zamknieciu appki timer nadal dziala jako watek a apka dziala w tle
        if(PlayerFrameController.timer != null)
            PlayerFrameController.timer.cancel();
        soundTrack.destroy();
        if(EndController.soundTrack != null) EndController.soundTrack.destroy();
    }

    public static void main(String[] args) {
        launch();
    }
}