package app.ageofspice.units_classes;


import app.ageofspice.Resourcesandcosts.*;
import app.ageofspice.TileType;

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

    }
}
