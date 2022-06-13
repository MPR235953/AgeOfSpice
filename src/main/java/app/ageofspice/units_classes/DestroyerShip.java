package app.ageofspice.units_classes;

import app.ageofspice.MapController;
import app.ageofspice.Resourcesandcosts.*;
import app.ageofspice.TileType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Ciezka  jednostka.
 * Statystyki które musi mieć.
 * -Mało hp
 * -Dużo dmg
 * -Mało  movspeeda;
 * -Drogi
 */

public class DestroyerShip extends unit {
    public static Cost staticBaseCost = new Cost(new AlgiRes(10),new SpiceRes(20),new VibraniumRes(20),new CrystalRes(40));
    public DestroyerShip(){
        baseDMG = 40;
        baseHP = 60;
        movementSpeed = 3;
        movementSpeedleft =movementSpeed;
        actualHP = baseHP;
        shipType = TileType.DESTROYER_SHIP;
        baseCost = new Cost(new AlgiRes(10),new SpiceRes(20),new VibraniumRes(20),new CrystalRes(40));
    }
}
