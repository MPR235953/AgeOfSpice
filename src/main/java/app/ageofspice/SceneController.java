package app.ageofspice;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneController {

    public static void switchToFXML(String FXML) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AgeOfSpiceApp.class.getResource(FXML));

        Scene scene = new Scene(fxmlLoader.load(), AgeOfSpiceApp.SCREEN_WIDTH, AgeOfSpiceApp.SCREEN_HEIGHT);
        scene.getStylesheets().add(String.valueOf(SceneController.class.getResource("style.css")));
        AgeOfSpiceApp.stage.setTitle("Age of Spice - " + FXML);
        AgeOfSpiceApp.stage.setFullScreen(true);
        AgeOfSpiceApp.stage.setScene(scene);
        AgeOfSpiceApp.stage.show();
    }

}
