package app.ageofspice;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.IOException;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

public class TimerForFrames {

    public void start(Label timeLabel){
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
                    }
                });
            }
        }, 0, 1000);
    }
}
