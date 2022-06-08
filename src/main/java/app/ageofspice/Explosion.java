package app.ageofspice;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import java.io.File;
import java.util.ArrayList;
import app.ageofspice.MapController;
import app.ageofspice.Species.SpeciesType;
import app.ageofspice.TileType;
import app.ageofspice.movement.ActualPosition;
import javafx.scene.image.Image;


import static app.ageofspice.MapController.SAS_SCALE;
import static app.ageofspice.MapController.SAS_SCALE_POS;

public class Explosion {
    public static ArrayList<String>  imageViewArrayList = new ArrayList<String>();

    public Explosion(){
        imageViewArrayList.add("src/main/resources/app/ageofspice/arts/explosion/explosion1.png");
        imageViewArrayList.add("src/main/resources/app/ageofspice/arts/explosion/explosion2.png");
        imageViewArrayList.add("src/main/resources/app/ageofspice/arts/explosion/explosion3.png");
        imageViewArrayList.add("src/main/resources/app/ageofspice/arts/explosion/explosion4.png");
        imageViewArrayList.add("src/main/resources/app/ageofspice/arts/explosion/explosion5.png");
        imageViewArrayList.add("src/main/resources/app/ageofspice/arts/explosion/explosion6.png");

    }
    public static ImageView createview(String s,int size,int posx,int posy){
        ImageView imageView = new ImageView();
        imageView.setFitHeight(size * SAS_SCALE);
        imageView.setFitWidth(size * SAS_SCALE);
        imageView.relocate(posx * MapController.TILE_SIZE + SAS_SCALE_POS, posy * MapController.TILE_SIZE + SAS_SCALE_POS);
        imageView.setImage(new Image(new File(s).toURI().toString()));
        MapController.staticPane.getChildren().add(imageView);
        return imageView;
    }

}
