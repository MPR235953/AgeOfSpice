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
    public static Cost staticBaseCost = new Cost(new AlgiRes(20),new SpiceRes(5),new VibraniumRes(20),new CrystalRes(20));
    public ExplorerShip(){
        baseDMG = 20;
        baseHP = 60;
        movementSpeed = 6;
        movementSpeedleft = movementSpeed;
        actualHP = baseHP;
        shipType = TileType.EXPLORER_SHIP;
        baseCost = new Cost(new AlgiRes(20),new SpiceRes(5),new VibraniumRes(20),new CrystalRes(20));
    }
}
