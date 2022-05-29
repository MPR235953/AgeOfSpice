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
    public ScoutShip(){
        baseDMG = 5;
        baseHP = 20;
        shipType = SCOUT_SHIP;
        movementSpeed = 3;
        movementSpeedleft = movementSpeed;
        actualHP = baseHP;
        baseCost = new Cost(new AlgiRes(10),new SpiceRes(20),new VibraniumRes(30),new CrystalRes(30));
    }
}


