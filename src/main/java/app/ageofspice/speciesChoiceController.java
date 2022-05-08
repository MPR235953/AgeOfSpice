package app.ageofspice;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


public class speciesChoiceController {
    @FXML TextField pl1_TextField, pl2_TextField, pl3_TextField;
    @FXML ImageView playButton;

    public void changeButton(){
        if(pl1_TextField.getText().equals("") || pl2_TextField.getText().equals("") || pl3_TextField.getText().equals(""))
            playButton.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/play_button_disable.png").toURI().toString()));
        else
            playButton.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/play_button_ready.png").toURI().toString()));
    }
}
