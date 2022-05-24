package app.ageofspice;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class JavFrameController implements Initializable {
    public static Pane framePane;
    @FXML private Label timeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int counter = 15;       // 2 minuty na ture
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if(counter == 10){ timeLabel.setTextFill(Color.RED); }
                    if(counter > 0){
                        String timeFormat = new String("");
                        if(counter / 60 < 10) timeFormat += "0" + counter / 60 + " : ";
                        else timeFormat += "0" + counter / 60 + " : ";
                        if(counter % 60 < 10) timeFormat += "0" + counter % 60;
                        else timeFormat += counter % 60;
                        timeLabel.setText(timeFormat);
                        counter--;
                    }
                    else{
                        timer.cancel();

                        //Przewidziane dalsze zmiany
                        ///TODO: Zakomentowac sobie fragment ponizej jesli kogos drazni przalacanie banerow

                        //od
                        try { changePlayer(); }
                        catch (IOException e) { e.printStackTrace(); }
                        //do

                    }
                });
            }
        }, 0, 1000);
    }

    public void changePlayer() throws IOException {
        framePane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ludFrame.fxml")));
        if(SzrFrameController.framePane != null) MapController.staticAnchorPane.getChildren().remove(SzrFrameController.framePane);
        if(MapController.framePane != null) MapController.staticAnchorPane.getChildren().remove(MapController.framePane);
        MapController.staticAnchorPane.getChildren().add(framePane);
    }
}
