package app.ageofspice.Buildings;

import app.ageofspice.Resourcesandcosts.*;

/**
 * Zmniejsza koszt budowy?
 */

public class LabStation extends absBuilding{
    public LabStation(){
        baseHP = 40;
        actualHP = baseHP;
        baseCost = new Cost(new AlgiRes(20),new SpiceRes(20),new VibraniumRes(30),new CrystalRes(10));
    }

    @Override
    void destroy() {

    }
}
