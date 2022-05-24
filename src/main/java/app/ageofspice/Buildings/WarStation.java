package app.ageofspice.Buildings;

import app.ageofspice.Resourcesandcosts.*;

/**
 * Daje jednostkom bonus do ataku
 */

public class WarStation extends absBuilding{
    public WarStation(){
        baseHP = 40;
        actualHP = baseHP;
        baseCost = new Cost(new AlgiRes(20),new SpiceRes(20),new VibraniumRes(30),new CrystalRes(20));
    }

    @Override
    void destroy() {

    }
}
