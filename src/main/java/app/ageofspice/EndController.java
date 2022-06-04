package app.ageofspice;

import app.ageofspice.Species.SpeciesColors;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class EndController implements Initializable {
    @FXML Pane javPane, ludPane, szrPane;
    @FXML Label winnerLabel, winLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switch(GameLoop.winner){
            case 0 -> javPane.relocate(650, 300);
            case 1 -> ludPane.relocate(650, 300);
            case 2 -> szrPane.relocate(650, 300);
        }
        winnerLabel.setText(GameLoop.playerResources[GameLoop.winner].getPlayerName());
        winLabel.setStyle("-fx-font-size: 100;" + "-fx-text-fill: " + SpeciesColors.ColorCSS[GameLoop.winner]);
        winnerLabel.setStyle("-fx-font-size: 60;" + "-fx-text-fill: " + SpeciesColors.ColorCSS[GameLoop.winner]);
    }
}
