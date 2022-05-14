package app.ageofspice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class SceneController {

    Stage stage;

    public SceneController(){}
    public SceneController(Stage stage){
        this.stage = stage;
    }

    public void makeScene(String fxmlFilePath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AgeOfSpiceApp.class.getResource(fxmlFilePath));
        //FXMLLoader fxmlLoader = new FXMLLoader(AgeOfSpiceApp.class.getResource("map.fxml"));

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        System.out.println(screenBounds);

        Scene scene = new Scene(fxmlLoader.load(), screenBounds.getWidth(), screenBounds.getHeight());
        //scene.getStylesheets().add(String.valueOf(getClass().getResource("style.css")));
        stage.setTitle("Age of Spice");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

}
