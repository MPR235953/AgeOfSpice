package app.ageofspice;

import app.ageofspice.Species.SpeciesColors;
import app.ageofspice.UnitandBuildingStorage.PlayerResourcesandUnitsStorage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import static app.ageofspice.GameLoop.*;
import static app.ageofspice.MapController.*;

public class PlayerFrameController implements Initializable{
    public static Timer timer;
    @FXML private Label timeLabel, roundNumberLabel, nickLabel;
    @FXML private Label spiceStatusLabel, crystalStatusLabel, algaStatusLabel, vibraniumStatusLabel;
    @FXML private Pane javPane, ludPane, szrPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timerInit();
    }

    public void refreshStats(){
        //ustawienie dopowiedniego pana w lewym gornym rogu
        if(framePane != null) {
            framePane.getChildren().remove(javPane);
            framePane.getChildren().remove(ludPane);
            framePane.getChildren().remove(szrPane);
        }
        //pokazanie odpowiedniego nickname i koloru w zaleznosci od numeru gracza w GameLoop
        nickLabel.setText(playerResources[playerNumber].getPlayerName());
        switch (playerNumber) {
            case 0 -> {
                nickLabel.setTextFill(SpeciesColors.javColor);
                if (framePane != null) framePane.getChildren().add(javPane);
                javPane.relocate(0, 0);
            }
            case 1 -> {
                nickLabel.setTextFill(SpeciesColors.ludColor);
                if (framePane != null) framePane.getChildren().add(ludPane);
                ludPane.relocate(0, 0);
            }
            case 2 -> {
                nickLabel.setTextFill(SpeciesColors.szrColor);
                if (framePane != null) framePane.getChildren().add(szrPane);
                szrPane.relocate(0, 0);
            }
        }
        //pokazanie aktualnych surowcow gracza w zaleznosci od nr gracza z GameLoop
        spiceStatusLabel.setText(String.valueOf(playerResources[playerNumber].getResources().przyprawa.quantity));
        crystalStatusLabel.setText(String.valueOf(playerResources[playerNumber].getResources().krysztal.quantity));
        algaStatusLabel.setText(String.valueOf(playerResources[playerNumber].getResources().algi.quantity));
        vibraniumStatusLabel.setText(String.valueOf(playerResources[playerNumber].getResources().wibranium.quantity));
        roundNumberLabel.setText("Tura   " + roundNumber);
    }

    public void timerInit(){
        //Aktywacja timera roboczo 15s bo chcialem zobaczyc jak sie kolorek zmienia
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int counter = 120;       // 2 minuty na ture
            @Override
            public void run() {
                Platform.runLater(() -> {
                    refreshStats();

                    if(counter <= 10) timeLabel.setTextFill(Color.RED);
                    else timeLabel.setTextFill(Color.WHITE);
                    if(counter > 0){
                        String timeFormat = "";
                        if(counter / 60 < 10) timeFormat += "0" + counter / 60 + " : ";
                        else timeFormat += "0" + counter / 60 + " : ";
                        if(counter % 60 < 10) timeFormat += "0" + counter % 60;
                        else timeFormat += counter % 60;
                        timeLabel.setText("Czas   " + timeFormat);
                        counter--;
                    }
                    else{
                        ///TODO: Zakomentowac sobie fragment ponizej jesli kogos drazni przalacanie banerow

                        //od
                        //nextPlayer = true;
                        changePlayer();
                        //do

                    }
                });
            }
        }, 0, 1000);
    }

    public void changePlayer() {
        if(timer != null) timer.cancel();       //stopowanie timera

        //zwiekszanie nr rundy i nr playera, ale to chyba bedzie ogarniach GameLoop
        playerResources[playerNumber].endturactions();



        int countPlayersDead =0;
        while(countPlayersDead<3)
        {
            playerNumber++;
            if(playerNumber == 3) playerNumber = 0;
            if (playerResources[playerNumber].Alive){
                break;
            }
            countPlayersDead++;
        }
        //###dodac funkcje do skonczenia gry
        //if (countPlayersDead==2)

        //###

        roundNumber++;
       /* for(int y = 0; y < MapController.VERTICAL_TILE_COUNT; y++) {
            for (int x = 0; x < MapController.HORIZONTAL_TILE_COUNT; x++) {
                if(board[x][y].active){

                }
            }
        }*/

        clearAllWindows();
        timerInit();
    }

    public void clearAllWindows(){
        for(int i = 0; i < arrayBuildWinForBuildings.size(); i++){
                arrayBuildWinForBuildings.get(i).closeWin(null);
                staticPane.getChildren().remove(arrayBuildWinForBuildings.get(i));
        }

        for(int i = 0; i < arrayBuildWinForParentalStation.size(); i++){
                arrayBuildWinForParentalStation.get(i).closeWin(null);
                staticPane.getChildren().remove(arrayBuildWinForParentalStation.get(i));
        }
        for(int i = 0; i < arrayOnClickSpaceWin.size(); i++){
                arrayOnClickSpaceWin.get(i).closeWin(null);
                staticPane.getChildren().remove(arrayOnClickSpaceWin.get(i));
        }
        for(int i = 0; i < arrayOnClickSpaceWinForUnits.size(); i++){
                arrayOnClickSpaceWinForUnits.get(i).closeWin(null);
                staticPane.getChildren().remove(arrayOnClickSpaceWinForUnits.get(i));
        }
    }

}
