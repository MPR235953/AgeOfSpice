package app.ageofspice;

import app.ageofspice.Species.SpeciesType;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;

import static app.ageofspice.GameLoop.playerResources;

public class SpeciesChoiceController {
    @FXML AnchorPane anchorPane;
    @FXML Pane alertPane;
    @FXML TextField pl1_TextField, pl2_TextField, pl3_TextField;
    @FXML ImageView playButton;

    public boolean diffNames(){
        if(pl1_TextField.getText().equals(pl2_TextField.getText()) ||
                pl1_TextField.getText().equals(pl3_TextField.getText()) ||
                pl2_TextField.getText().equals(pl3_TextField.getText())) return false;
        else return true;
    }

    public void createPlayers(){
        if(diffNames()){
            playerResources[0].setPlayerName(pl1_TextField.getText());
            playerResources[1].setPlayerName(pl2_TextField.getText());
            playerResources[2].setPlayerName(pl3_TextField.getText());

            playerResources[0].setSpeciesType(SpeciesType.JAVALERZY);
            playerResources[1].setSpeciesType(SpeciesType.LUDZIE);
            playerResources[2].setSpeciesType(SpeciesType.SZRUNGALE);

            for(int i = 0; i < playerResources.length; i++)
                playerResources[i].setSpeciesColor();
        }
    }

    public void alertAppear(){ alertPane.relocate(playButton.getLayoutX() - (alertPane.getWidth() - playButton.getFitWidth()) / 2,  playButton.getLayoutY()); }
    public void alertDisappear(){ alertPane.relocate(playButton.getLayoutX(),  AgeOfSpiceApp.SCREEN_HEIGHT * 2); }
    public void tryAgain(ActionEvent event){ alertDisappear(); }

    public void changeButton(){
        if(pl1_TextField.getText().equals("") || pl2_TextField.getText().equals("") || pl3_TextField.getText().equals(""))
            playButton.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/play_button_disable.png").toURI().toString()));
        else if(!diffNames())
            alertAppear();
        else {
            createPlayers();

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

}
