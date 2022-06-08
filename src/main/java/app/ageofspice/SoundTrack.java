package app.ageofspice;

import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class SoundTrack{
    private ArrayList<File> songs;
    private File directory;
    private File[] files;
    private Media media;
    private MediaPlayer mediaPlayer;

    private int songNumber = 0;

    public Timer timer;
    public TimerTask task;

    private boolean running;

    public void init(String path){
        songs = new ArrayList<File>();
        directory = new File(path);
        files = directory.listFiles();

        if(files != null) {
            for(File file : files)
                songs.add(file);
        }

        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    public void playMedia() {
        beginTimer();
        mediaPlayer.play();
    }

    public void pauseMedia() {
        cancelTimer();
        mediaPlayer.pause();
    }

    public void nextMedia() {
        if(songNumber < songs.size() - 1) {
            songNumber++;
            mediaPlayer.stop();
            if(running)
                cancelTimer();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            playMedia();
        }
        else {
            songNumber = 0;
            mediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            playMedia();
        }
    }

    public void beginTimer() {
        timer = new Timer();
        task = new TimerTask() {
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                if(current/end == 1)
                    cancelTimer();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void cancelTimer() {
        running = false;
        timer.cancel();
        nextMedia();
    }

    public void destroy(){
        mediaPlayer.pause();
        task.cancel();
        timer.cancel();
    }
}
