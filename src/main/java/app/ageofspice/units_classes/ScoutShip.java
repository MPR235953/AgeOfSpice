package app.ageofspice.units_classes;

import app.ageofspice.MapController;
import app.ageofspice.Resourcesandcosts.*;
import app.ageofspice.TileType;
import app.ageofspice.movement.ActualPosition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

import static app.ageofspice.TileType.SCOUT_SHIP;

/**
 * Szybka jednostka.
 * Statystyki które musi mieć.
 * -Mało hp
 * -Mało dmg
 * -Duzo movspeeda;
 * -Tani
 */

public class ScoutShip extends unit{
    public static Cost staticBaseCost = new Cost(new AlgiRes(20),new SpiceRes(15),new VibraniumRes(10),new CrystalRes(20));
    public ScoutShip(){
        baseDMG = 10;
        baseHP = 40;
        shipType = SCOUT_SHIP;
        movementSpeed = 9;
        movementSpeedleft = movementSpeed;
        actualHP = baseHP;
        baseCost = new Cost(new AlgiRes(20),new SpiceRes(15),new VibraniumRes(10),new CrystalRes(20));
    }
}


