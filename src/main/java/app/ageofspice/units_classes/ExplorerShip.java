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
        baseDMG = 100;
        baseHP = 40;
        movementSpeed = 10;
        movementSpeedleft = movementSpeed;
        actualHP = baseHP;
        shipType = TileType.EXPLORER_SHIP;
        baseCost = new Cost(new AlgiRes(10),new SpiceRes(20),new VibraniumRes(30),new CrystalRes(30));
    }
}
