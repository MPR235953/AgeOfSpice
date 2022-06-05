package app.ageofspice.units_classes;

import app.ageofspice.MapController;
import app.ageofspice.Species.SpeciesType;
import app.ageofspice.TileType;
import app.ageofspice.movement.ActualPosition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

import static app.ageofspice.MapController.SAS_SCALE;
import static app.ageofspice.MapController.SAS_SCALE_POS;

public class Lasers {
    public SpeciesType speciesType;
    public ImageView imageView = new ImageView();
    public String path;
    public ActualPosition position;

    public void lasersImviewCreate(){
        this.imageView = new ImageView();
        this.imageView.setFitHeight(40 * SAS_SCALE);
        this.imageView.setFitWidth(40 * SAS_SCALE);
        this.imageView.relocate(position.x * MapController.TILE_SIZE + SAS_SCALE_POS, position.y * MapController.TILE_SIZE + SAS_SCALE_POS);
        this.imageView.setImage(new Image(new File(path).toURI().toString()));
        MapController.staticPane.getChildren().add(this.imageView);

    }

    public void destroyView(){
        MapController.staticPane.getChildren().remove(this.imageView);
        position.x = 0;
        position.y = 0;
    }
}
