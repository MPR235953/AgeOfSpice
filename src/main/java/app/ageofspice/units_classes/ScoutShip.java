package app.ageofspice.units_classes;

import app.ageofspice.Resourcesandcosts.*;

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
        movementSpeed = 3;
        actualHP = baseHP;
        baseCost = new Cost(new AlgiRes(10),new SpiceRes(20),new VibraniumRes(30),new CrystalRes(30));
    }
}
