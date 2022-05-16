package app.ageofspice.units_classes;


import app.ageofspice.Resourcesandcosts.*;

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
        movementSpeed = 2;
        actualHP = baseHP;
        baseCost = new Cost(new AlgiRes(10),new SpiceRes(20),new VibraniumRes(30),new CrystalRes(30));
    }
}
