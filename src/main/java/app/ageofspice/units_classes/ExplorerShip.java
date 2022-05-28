package app.ageofspice.units_classes;


import app.ageofspice.MapController;
import app.ageofspice.Resourcesandcosts.*;
import app.ageofspice.TileType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Podstawowa  jednostka.
 * Statystyki które musi mieć.
 * -Srednio  hp
 * -Srednio dmg
 * -Srednio  movspeeda;
 * -Normalny koszt
 */


public class ExplorerShip extends unit{
    public ExplorerShip(){
        baseDMG = 10;
        baseHP = 40;
        movementSpeed = 3;
        movementSpeedleft = movementSpeed;
        actualHP = baseHP;
        shipType = TileType.EXPLORER_SHIP;
        baseCost = new Cost(new AlgiRes(10),new SpiceRes(20),new VibraniumRes(30),new CrystalRes(30));
    }

    @Override
    public void imageviewconstructor(String imview) {
        this.imageView = new ImageView();
        this.imageView.setFitHeight(MapController.TILE_SIZE);
        this.imageView.setFitWidth(MapController.TILE_SIZE);
        this.imageView.relocate(position.x * MapController.TILE_SIZE, position.y * MapController.TILE_SIZE);
        this.imageView.setImage(new Image(new File(imview).toURI().toString()));
        MapController.staticPane.getChildren().add(this.imageView);


    }
}
