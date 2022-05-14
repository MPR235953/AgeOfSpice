package app.ageofspice;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class speciesChoiceController {
    @FXML TextField pl1_TextField, pl2_TextField, pl3_TextField;
    @FXML ImageView playButton;

    //SceneController sceneController = new SceneController();

    public void changeButton(){
        if(pl1_TextField.getText().equals("") || pl2_TextField.getText().equals("") || pl3_TextField.getText().equals(""))
            playButton.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/play_button_disable.png").toURI().toString()));
        else {
            playButton.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/play_button_ready.png").toURI().toString()));

            playButton.setStyle("-fx-cursor: hand;");

            playButton.setOnMouseClicked(event -> {
                try {
                    SceneController.switchToFXML("map.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            playButton.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
                if (newValue) {
                    playButton.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/play_button_active.png").toURI().toString()));
                } else {
                    playButton.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/play_button_ready.png").toURI().toString()));
                }
            });
        }
    }

    /*playButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent t) {
        rect.setFill(Color.RED);
    }
    });*/
}
