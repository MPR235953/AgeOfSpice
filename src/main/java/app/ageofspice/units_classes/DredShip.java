package app.ageofspice.units_classes;


import app.ageofspice.MapController;
import app.ageofspice.Resourcesandcosts.*;
import app.ageofspice.TileType;
import app.ageofspice.movement.ActualPosition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Ciezka  jednostka.
 * Statystyki które musi mieć.
 * -Dużo hp
 * -Srednio dmg
 * -Mało  movspeeda;
 * -Drogi
 */

public class DredShip extends unit{
    public static Cost staticBaseCost = new Cost(new AlgiRes(20),new SpiceRes(15),new VibraniumRes(30),new CrystalRes(20));
    public DredShip(){
        shipType = TileType.DRED_SHIP;
        baseDMG = 20;
        baseHP = 100;
        movementSpeed = 3;
        movementSpeedleft =movementSpeed;
        actualHP = baseHP;
        baseCost = new Cost(new AlgiRes(20),new SpiceRes(15),new VibraniumRes(30),new CrystalRes(20));
    }
}
