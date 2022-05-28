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
 * -Dużo hp
 * -Srednio dmg
 * -Mało  movspeeda;
 * -Drogi
 */

public class DredShip extends unit{
    public DredShip(){
        baseDMG = 10;
        baseHP = 40;
        movementSpeed = 2;
        actualHP = baseHP;
        baseCost = new Cost(new AlgiRes(10),new SpiceRes(20),new VibraniumRes(30),new CrystalRes(30));
    }
}
