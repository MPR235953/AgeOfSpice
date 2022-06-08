package app.ageofspice;

import app.ageofspice.Species.SpeciesColors;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class EndController implements Initializable {
    @FXML Pane javPane, ludPane, szrPane;
    @FXML Label winnerLabel, winLabel;
    @FXML AnchorPane anchorPane;
    public static AnchorPane staticAnchorPane;
    public static SoundTrack soundTrack = new SoundTrack();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(AgeOfSpiceApp.soundTrack != null) AgeOfSpiceApp.soundTrack.destroy();
        soundTrack.init("src/main/resources/app/ageofspice/egg");
        soundTrack.playMedia();

        staticAnchorPane = anchorPane;
        switch(GameLoop.winner){
            case 0 -> javPane.relocate(600, 250);
            case 1 -> ludPane.relocate(600, 250);
            case 2 -> szrPane.relocate(600, 250);
        }
        winnerLabel.setText(GameLoop.playerResources[GameLoop.winner].getPlayerName());
        winLabel.setStyle("-fx-font-size: 100;" + "-fx-text-fill: " + SpeciesColors.ColorCSS[GameLoop.winner]);
        winnerLabel.setStyle("-fx-font-size: 60;" + "-fx-text-fill: " + SpeciesColors.ColorCSS[GameLoop.winner]);
    }
}
