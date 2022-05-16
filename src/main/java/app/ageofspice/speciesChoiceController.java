package app.ageofspice;

import app.ageofspice.UnitandBuildingStorage.PlayerResourcesandUnitsStorage;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;

public class speciesChoiceController {
    @FXML AnchorPane anchorPane;
    @FXML Pane alertPane;
    @FXML TextField pl1_TextField, pl2_TextField, pl3_TextField;
    @FXML ImageView playButton;

    public static PlayerResourcesandUnitsStorage[] player = {   new PlayerResourcesandUnitsStorage(),
                                                                new PlayerResourcesandUnitsStorage(),
                                                                new PlayerResourcesandUnitsStorage()
                                                            };

    public boolean diffNames(){
        if(pl1_TextField.getText().equals(pl2_TextField.getText()) ||
                pl1_TextField.getText().equals(pl3_TextField.getText()) ||
                pl2_TextField.getText().equals(pl3_TextField.getText())) return false;
        else return true;
    }

    public void createPlayers(){
        if(diffNames()){
            player[0].setPlayerName(pl1_TextField.getText());
            player[1].setPlayerName(pl2_TextField.getText());
            player[2].setPlayerName(pl3_TextField.getText());

            player[0].setSpeciesType(SpeciesType.JAVALERZY);
            player[1].setSpeciesType(SpeciesType.LUDZIE);
            player[2].setSpeciesType(SpeciesType.SZRUNGALE);
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
