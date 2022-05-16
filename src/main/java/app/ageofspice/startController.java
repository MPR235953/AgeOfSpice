package app.ageofspice;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class startController implements Initializable {
    @FXML ImageView startButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startButton.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/start2.png").toURI().toString()));

        startButton.setStyle("-fx-cursor: hand;");

        startButton.setOnMouseClicked(event -> {
            try {
                SceneController.switchToFXML("speciesChoice.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //#############  grafika do podmianki

        /*startButton.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                startButton.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/play_button_active.png").toURI().toString()));
            } else {
                startButton.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/play_button_ready.png").toURI().toString()));
            }
        });*/
    }

}
