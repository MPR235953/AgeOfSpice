package app.ageofspice;

import app.ageofspice.Species.SpeciesColors;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
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
    @FXML private Button pauseButton, restoreButton;
    private final Pane unactivePane = new Pane();
    private boolean stopTimerFlag = false;

    public void pause(){
        /*if(AgeOfSpiceApp.soundTrack != null) AgeOfSpiceApp.soundTrack.pauseMedia();
        if(EndController.soundTrack != null) EndController.soundTrack.pauseMedia();*/
        unactivePane.setPrefHeight(AgeOfSpiceApp.SCREEN_HEIGHT);
        unactivePane.setPrefWidth(AgeOfSpiceApp.SCREEN_WIDTH);
        unactivePane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75);");
        unactivePane.relocate(0, AgeOfSpiceApp.FRAME_SIZE);
        staticAnchorPane.getChildren().add(unactivePane);
        restoreButton.toFront();
        stopTimerFlag = true;
    }

    public void restore(){
        /*if(AgeOfSpiceApp.soundTrack != null) AgeOfSpiceApp.soundTrack.playMedia();
        if(EndController.soundTrack != null) EndController.soundTrack.playMedia();*/
        staticAnchorPane.getChildren().remove(unactivePane);
        pauseButton.toFront();
        stopTimerFlag = false;
    }

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
        roundNumberLabel.setText("Round   " + roundNumber);
    }

    public void timerInit(){
        //Aktywacja timera roboczo 15s bo chcialem zobaczyc jak sie kolorek zmienia
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int counter = 90;       // 2 minuty na ture
            @Override
            public void run() {
                Platform.runLater(() -> {
                    refreshStats();
                    try { searchWinner(); }
                    catch (IOException e) { e.printStackTrace(); }

                    if(counter > 0){
                        String timeFormat = "";
                        if(counter / 60 < 10) timeFormat += "0" + counter / 60 + " : ";
                        else timeFormat += "0" + counter / 60 + " : ";
                        if(counter % 60 < 10) timeFormat += "0" + counter % 60;
                        else timeFormat += counter % 60;
                        if(!stopTimerFlag) {
                            if(counter <= 10) timeLabel.setTextFill(Color.RED);
                            else timeLabel.setTextFill(Color.WHITE);
                            timeLabel.setText("Time   " + timeFormat);
                            counter--;
                        }
                    }
                    else changePlayer();
                });
            }
        }, 0, 1000);
    }

    public void searchWinner() throws IOException {
        int deadPlayers = 0;
        for(int i = 0; i < playerResources.length; i++){
            if(!playerResources[i].Alive)
                deadPlayers++;
            else{
                winner = i;
                //przypadek zdobycia wygrywajacej liczby przyprawy
                if(playerResources[i].getResources().przyprawa.quantity >= WINABLE_SPICE_QUANTITY){
                    if(timer != null) timer.cancel();
                    SceneController.switchToFXML("end.fxml");
                }
            }
        }
        //przypadek pozostania jedynym graczem na planszy
        if(deadPlayers == 2) {
            if(timer != null) timer.cancel();
            SceneController.switchToFXML("end.fxml");
        }
    }

    public void changePlayer(){
        if(timer != null) timer.cancel();       //stopowanie timera
        restore();


        //zwiekszanie nr rundy i nr playera, ale to chyba bedzie ogarniach GameLoop
        playerResources[playerNumber].endturactions();

        //petla tylko do tego aby nie byl wyswietlany banner martwego gracza (nie jest to petla na warunek wygranej ogarnia to metoda searchWinner)
        int countPlayersDead =0;
        while(countPlayersDead<3) {
            playerNumber++;
            if(playerNumber == 3) playerNumber = 0;
            if (playerResources[playerNumber].Alive){
                break;
            }
            countPlayersDead++;
        }

        roundNumber++;

        clearAllWindows();
        timerInit();
    }

    public static void clearAllWindows(){
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
        for(int i = 0; i < arrayOnClickSpaceWinForBuildings.size(); i++){
            arrayOnClickSpaceWinForBuildings.get(i).closeWin(null);
            staticPane.getChildren().remove(arrayOnClickSpaceWinForBuildings.get(i));
        }
    }

}
